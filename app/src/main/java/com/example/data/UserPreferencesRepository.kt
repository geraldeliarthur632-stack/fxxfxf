package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class UserPreferencesRepository(context: Context) {
    private val appContext = context.applicationContext
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val prefs: SharedPreferences = context.getSharedPreferences("mundo_da_musica_prefs", Context.MODE_PRIVATE)

    private val _userProfile = MutableStateFlow(loadProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _achievements = MutableStateFlow(loadAchievements())
    val achievements: StateFlow<List<Achievement>> = _achievements.asStateFlow()

    init {
        checkAndApply12HourRefill()
        updateStreak()
    }

    fun hasCompletedOnboarding(): Boolean {
        return prefs.getBoolean("has_completed_onboarding", false)
    }

    fun completeOnboarding() {
        prefs.edit().putBoolean("has_completed_onboarding", true).apply()
    }

    private fun loadProfile(): UserProfile {
        val userId = prefs.getString("user_id", "guest_" + UUID.randomUUID().toString().take(6)) ?: "guest_user"
        val nickname = prefs.getString("nickname", "Músico Curioso") ?: "Músico Curioso"
        val email = prefs.getString("email", null)
        val isGuest = prefs.getBoolean("is_guest", true)
        val avatarId = prefs.getString("avatar_id", "pipo_avatar") ?: "pipo_avatar"
        val coins = prefs.getInt("coins", 3)
        val lastRefillTimestamp = prefs.getLong("last_refill_ts", System.currentTimeMillis())
        val streak = prefs.getInt("current_streak", 1)
        val lastActiveDate = prefs.getString("last_active_date", "") ?: ""
        val xp = prefs.getInt("total_xp", 0)
        val instrumentId = prefs.getString("selected_instrument", InstrumentType.VIOLAO.id)
        val selectedInstrument = InstrumentType.entries.firstOrNull { it.id == instrumentId } ?: InstrumentType.VIOLAO
        val langCode = prefs.getString("language", AppLanguage.PT.code) ?: AppLanguage.PT.code
        val language = AppLanguage.entries.firstOrNull { it.code == langCode } ?: AppLanguage.PT
        val isDarkMode = prefs.getBoolean("is_dark_mode", false)
        val soundEffects = prefs.getBoolean("sound_effects", true)
        val haptic = prefs.getBoolean("haptic_feedback", true)

        val unlocked = prefs.getStringSet("unlocked_lessons", emptySet()) ?: emptySet()
        val completed = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()

        val transList = mutableListOf<CoinTransaction>()
        val transJson = prefs.getString("transactions_json", "[]") ?: "[]"
        try {
            val arr = JSONArray(transJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                transList.add(
                    CoinTransaction(
                        id = obj.getString("id"),
                        amount = obj.getInt("amount"),
                        type = TransactionType.valueOf(obj.getString("type")),
                        timestamp = obj.getLong("timestamp"),
                        description = obj.getString("description")
                    )
                )
            }
        } catch (_: Exception) {}

        if (transList.isEmpty()) {
            transList.add(
                CoinTransaction(
                    id = "init_bonus",
                    amount = 3,
                    type = TransactionType.INITIAL_GIFT,
                    description = "Bônus Inicial de Boas-vindas (3 Moedas)"
                )
            )
        }

        return UserProfile(
            userId = userId,
            nickname = nickname,
            email = email,
            isGuest = isGuest,
            avatarId = avatarId,
            coins = coins,
            lastRefillTimestamp = lastRefillTimestamp,
            currentStreak = streak,
            lastActiveDate = lastActiveDate,
            totalXp = xp,
            selectedInstrument = selectedInstrument,
            language = language,
            isDarkMode = isDarkMode,
            soundEffectsEnabled = soundEffects,
            hapticFeedbackEnabled = haptic,
            unlockedLessonIds = unlocked,
            completedLessonIds = completed,
            transactions = transList
        )
    }

    private fun saveProfile(profile: UserProfile) {
        prefs.edit()
            .putString("user_id", profile.userId)
            .putString("nickname", profile.nickname)
            .putString("email", profile.email)
            .putBoolean("is_guest", profile.isGuest)
            .putString("avatar_id", profile.avatarId)
            .putInt("coins", profile.coins)
            .putLong("last_refill_ts", profile.lastRefillTimestamp)
            .putInt("current_streak", profile.currentStreak)
            .putString("last_active_date", profile.lastActiveDate)
            .putInt("total_xp", profile.totalXp)
            .putString("selected_instrument", profile.selectedInstrument.id)
            .putString("language", profile.language.code)
            .putBoolean("is_dark_mode", profile.isDarkMode)
            .putBoolean("sound_effects", profile.soundEffectsEnabled)
            .putBoolean("haptic_feedback", profile.hapticFeedbackEnabled)
            .putStringSet("unlocked_lessons", profile.unlockedLessonIds)
            .putStringSet("completed_lessons", profile.completedLessonIds)
            .putString("transactions_json", serializeTransactions(profile.transactions))
            .apply()

        _userProfile.value = profile
        checkAchievements(profile)

        scope.launch {
            FirebaseManager.syncProfileToFirestore(appContext, profile)
        }
    }

    private fun serializeTransactions(list: List<CoinTransaction>): String {
        val arr = JSONArray()
        list.takeLast(50).forEach { item ->
            val obj = JSONObject()
            obj.put("id", item.id)
            obj.put("amount", item.amount)
            obj.put("type", item.type.name)
            obj.put("timestamp", item.timestamp)
            obj.put("description", item.description)
            arr.put(obj)
        }
        return arr.toString()
    }

    fun checkAndApply12HourRefill() {
        val current = _userProfile.value
        val now = System.currentTimeMillis()
        if (current.coins < UserProfile.MAX_FREE_COINS) {
            val elapsed = now - current.lastRefillTimestamp
            if (elapsed >= UserProfile.REFILL_INTERVAL_MILLIS) {
                val coinsToAdd = UserProfile.MAX_FREE_COINS - current.coins
                val newCoins = UserProfile.MAX_FREE_COINS
                val newTrans = current.transactions + CoinTransaction(
                    id = "refill_${System.currentTimeMillis()}",
                    amount = coinsToAdd,
                    type = TransactionType.REFILL_12H,
                    description = "Recarga Automática de 12 Horas (+$coinsToAdd)"
                )
                saveProfile(
                    current.copy(
                        coins = newCoins,
                        lastRefillTimestamp = now,
                        transactions = newTrans
                    )
                )
            }
        }
    }

    fun updateStreak() {
        val current = _userProfile.value
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        if (current.lastActiveDate.isEmpty()) {
            saveProfile(current.copy(lastActiveDate = today, currentStreak = 1))
        } else if (current.lastActiveDate != today) {
            val calToday = Calendar.getInstance()
            val calLast = Calendar.getInstance()
            try {
                val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                df.parse(current.lastActiveDate)?.let { calLast.time = it }
                calLast.add(Calendar.DAY_OF_YEAR, 1)
                val isConsecutive = calToday.get(Calendar.YEAR) == calLast.get(Calendar.YEAR) &&
                        calToday.get(Calendar.DAY_OF_YEAR) == calLast.get(Calendar.DAY_OF_YEAR)

                val newStreak = if (isConsecutive) current.currentStreak + 1 else 1
                saveProfile(current.copy(lastActiveDate = today, currentStreak = newStreak))
            } catch (_: Exception) {
                saveProfile(current.copy(lastActiveDate = today))
            }
        }
    }

    fun canAccessLesson(lessonId: String): Boolean {
        val profile = _userProfile.value
        if (profile.unlockedLessonIds.contains(lessonId) || profile.completedLessonIds.contains(lessonId)) {
            return true
        }
        return profile.coins >= 1
    }

    fun unlockOrStartLesson(lessonId: String): Boolean {
        checkAndApply12HourRefill()
        val current = _userProfile.value
        if (current.unlockedLessonIds.contains(lessonId) || current.completedLessonIds.contains(lessonId)) {
            return true // Already unlocked, 0 coins required
        }

        if (current.coins < 1) {
            return false // Insufficient coins
        }

        val newCoins = current.coins - 1
        val newUnlocked = current.unlockedLessonIds + lessonId
        val newTrans = current.transactions + CoinTransaction(
            id = "lesson_${System.currentTimeMillis()}",
            amount = -1,
            type = TransactionType.LESSON_UNLOCK,
            description = "Início da Aula ($lessonId)"
        )

        // If coins just dropped below MAX_FREE_COINS, start the 12h countdown timer from now
        val newRefillTs = if (current.coins == UserProfile.MAX_FREE_COINS) System.currentTimeMillis() else current.lastRefillTimestamp

        saveProfile(
            current.copy(
                coins = newCoins,
                lastRefillTimestamp = newRefillTs,
                unlockedLessonIds = newUnlocked,
                transactions = newTrans
            )
        )
        return true
    }

    fun completeLesson(lessonId: String, xpEarned: Int = 25) {
        val current = _userProfile.value
        val newCompleted = current.completedLessonIds + lessonId
        val newXp = current.totalXp + xpEarned
        saveProfile(
            current.copy(
                completedLessonIds = newCompleted,
                totalXp = newXp
            )
        )
        updateStreak()
    }

    fun addRewardAdCoin() {
        val current = _userProfile.value
        val newCoins = current.coins + 1
        val newTrans = current.transactions + CoinTransaction(
            id = "ad_${System.currentTimeMillis()}",
            amount = 1,
            type = TransactionType.REWARDED_AD,
            description = "Recompensa por Anúncio (+1 Moeda)"
        )
        saveProfile(
            current.copy(
                coins = newCoins,
                transactions = newTrans
            )
        )
    }

    fun purchaseCoins(amount: Int, totalPriceBrl: Double) {
        val current = _userProfile.value
        val newCoins = current.coins + amount
        val formattedPrice = String.format(Locale.US, "%.2f", totalPriceBrl)
        val newTrans = current.transactions + CoinTransaction(
            id = "pur_${System.currentTimeMillis()}",
            amount = amount,
            type = TransactionType.STORE_PURCHASE,
            description = "Compra de $amount Moedas (R$ $formattedPrice)"
        )
        saveProfile(
            current.copy(
                coins = newCoins,
                transactions = newTrans
            )
        )
    }

    fun setNickname(nickname: String) {
        saveProfile(_userProfile.value.copy(nickname = nickname))
    }

    fun setSelectedInstrument(instrument: InstrumentType) {
        saveProfile(_userProfile.value.copy(selectedInstrument = instrument))
    }

    fun setLanguage(language: AppLanguage) {
        saveProfile(_userProfile.value.copy(language = language))
    }

    fun setDarkMode(enabled: Boolean) {
        saveProfile(_userProfile.value.copy(isDarkMode = enabled))
    }

    fun setSoundEffects(enabled: Boolean) {
        saveProfile(_userProfile.value.copy(soundEffectsEnabled = enabled))
    }

    fun setHapticFeedback(enabled: Boolean) {
        saveProfile(_userProfile.value.copy(hapticFeedbackEnabled = enabled))
    }

    fun setAvatar(avatarId: String) {
        saveProfile(_userProfile.value.copy(avatarId = avatarId))
    }

    fun registerAccount(email: String, password: String, nickname: String): Boolean {
        val cleanEmail = email.trim().lowercase()
        val accountsJson = prefs.getString("local_accounts_json", "{}") ?: "{}"
        val accountsObj = try { JSONObject(accountsJson) } catch (_: Exception) { JSONObject() }
        
        val userObj = JSONObject().apply {
            put("email", cleanEmail)
            put("password", password)
            put("nickname", nickname.ifBlank { cleanEmail.substringBefore("@") })
            put("registeredAt", System.currentTimeMillis())
        }
        accountsObj.put(cleanEmail, userObj)
        prefs.edit().putString("local_accounts_json", accountsObj.toString()).apply()
        
        linkAccount(cleanEmail, nickname.ifBlank { cleanEmail.substringBefore("@") })
        return true
    }

    fun authenticateAccount(email: String, password: String): Boolean {
        val cleanEmail = email.trim().lowercase()
        val accountsJson = prefs.getString("local_accounts_json", "{}") ?: "{}"
        val accountsObj = try { JSONObject(accountsJson) } catch (_: Exception) { JSONObject() }
        
        if (accountsObj.has(cleanEmail)) {
            val userObj = accountsObj.getJSONObject(cleanEmail)
            val storedPass = userObj.optString("password")
            if (storedPass == password) {
                val nick = userObj.optString("nickname", cleanEmail.substringBefore("@"))
                linkAccount(cleanEmail, nick)
                return true
            }
            return false
        }
        
        // Auto-register seamless fallback if password is valid
        if (password.length >= 6) {
            registerAccount(cleanEmail, password, cleanEmail.substringBefore("@"))
            return true
        }
        return false
    }

    fun loginWithGoogleAccount(email: String, displayName: String? = null) {
        val cleanEmail = email.trim().lowercase()
        val name = displayName ?: cleanEmail.substringBefore("@").replaceFirstChar { it.uppercase() }
        registerAccount(cleanEmail, "google_oauth_token", name)
        linkAccount(cleanEmail, name)
    }

    fun linkAccount(email: String, nickname: String?) {
        val current = _userProfile.value
        saveProfile(
            current.copy(
                email = email,
                nickname = nickname ?: current.nickname,
                isGuest = false
            )
        )
    }

    fun logout() {
        // Reset to guest session while keeping general preferences
        val guestId = "guest_" + UUID.randomUUID().toString().take(6)
        saveProfile(
            _userProfile.value.copy(
                userId = guestId,
                email = null,
                isGuest = true,
                nickname = "Músico Convidado"
            )
        )
    }

    fun deleteAccount() {
        // Google Play policy compliant permanent account and data deletion
        val currentEmail = _userProfile.value.email
        if (currentEmail != null) {
            val accountsJson = prefs.getString("local_accounts_json", "{}") ?: "{}"
            try {
                val accountsObj = JSONObject(accountsJson)
                accountsObj.remove(currentEmail.trim().lowercase())
                prefs.edit().putString("local_accounts_json", accountsObj.toString()).apply()
            } catch (_: Exception) {}
        }

        prefs.edit()
            .remove("user_id")
            .remove("nickname")
            .remove("email")
            .remove("is_guest")
            .remove("avatar_id")
            .remove("coins")
            .remove("last_refill_ts")
            .remove("current_streak")
            .remove("last_active_date")
            .remove("total_xp")
            .remove("selected_instrument")
            .remove("unlocked_lessons")
            .remove("completed_lessons")
            .remove("transactions_json")
            .putBoolean("has_completed_onboarding", false)
            .apply()

        _userProfile.value = loadProfile()
    }

    private fun loadAchievements(): List<Achievement> {
        return listOf(
            Achievement("first_step", "Primeiro Som", "First Sound", "Primer Sonido", "Conclua sua primeira aula em qualquer instrumento.", "Complete your first lesson.", "Completa tu primera lección.", "music_note"),
            Achievement("streak_3", "Foco Musical", "Musical Focus", "Enfoque Musical", "Mantenha uma ofensiva de 3 dias de estudo.", "Reach a 3-day streak.", "Alcanza una racha de 3 días.", "local_fire_department"),
            Achievement("streak_7", "Músico Dedicado", "Dedicated Musician", "Músico Dedicado", "Mantenha uma ofensiva de 7 dias consecutivos.", "Reach a 7-day streak.", "Alcanza una racha de 7 días.", "whatshot"),
            Achievement("lessons_10", "Embalado", "In the Groove", "En Ritmo", "Conclua 10 aulas com sucesso.", "Complete 10 lessons.", "Completa 10 lecciones.", "star"),
            Achievement("module_master", "Mestre do Módulo", "Module Master", "Maestro del Módulo", "Complete todas as aulas de um módulo inteiro.", "Complete an entire module.", "Completa un módulo completo.", "workspace_premium"),
            Achievement("polyglot", "Multi-Instrumentista", "Multi-Instrumentalist", "Multi-Instrumentista", "Toque e aprenda com mais de um instrumento.", "Learn on multiple instruments.", "Aprende en más de un instrumento.", "piano"),
            Achievement("theory_whiz", "Sábio da Teoria", "Theory Whiz", "Sabio de la Teoría", "Acerte 10 perguntas de teoria musical seguidas.", "Answer 10 theory questions right.", "Acierta 10 preguntas de teoría.", "school"),
            Achievement("virtuoso", "Virtuoso", "Virtuoso", "Virtuoso", "Complete 50 aulas em sua jornada.", "Complete 50 lessons.", "Completa 50 lecciones.", "military_tech")
        )
    }

    private fun checkAchievements(profile: UserProfile) {
        val currentAchievements = _achievements.value.toMutableList()
        var changed = false

        if (profile.completedLessonIds.isNotEmpty()) {
            val idx = currentAchievements.indexOfFirst { it.id == "first_step" }
            if (idx != -1 && !currentAchievements[idx].isUnlocked) {
                currentAchievements[idx] = currentAchievements[idx].copy(isUnlocked = true, unlockedAt = System.currentTimeMillis())
                changed = true
            }
        }

        if (profile.currentStreak >= 3) {
            val idx = currentAchievements.indexOfFirst { it.id == "streak_3" }
            if (idx != -1 && !currentAchievements[idx].isUnlocked) {
                currentAchievements[idx] = currentAchievements[idx].copy(isUnlocked = true, unlockedAt = System.currentTimeMillis())
                changed = true
            }
        }

        if (profile.currentStreak >= 7) {
            val idx = currentAchievements.indexOfFirst { it.id == "streak_7" }
            if (idx != -1 && !currentAchievements[idx].isUnlocked) {
                currentAchievements[idx] = currentAchievements[idx].copy(isUnlocked = true, unlockedAt = System.currentTimeMillis())
                changed = true
            }
        }

        if (profile.completedLessonIds.size >= 10) {
            val idx = currentAchievements.indexOfFirst { it.id == "lessons_10" }
            if (idx != -1 && !currentAchievements[idx].isUnlocked) {
                currentAchievements[idx] = currentAchievements[idx].copy(isUnlocked = true, unlockedAt = System.currentTimeMillis())
                changed = true
            }
        }

        if (profile.completedLessonIds.size >= 50) {
            val idx = currentAchievements.indexOfFirst { it.id == "virtuoso" }
            if (idx != -1 && !currentAchievements[idx].isUnlocked) {
                currentAchievements[idx] = currentAchievements[idx].copy(isUnlocked = true, unlockedAt = System.currentTimeMillis())
                changed = true
            }
        }

        if (changed) {
            _achievements.value = currentAchievements
        }
    }
}
