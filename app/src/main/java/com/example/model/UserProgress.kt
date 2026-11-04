package com.example.model

enum class TransactionType {
    INITIAL_GIFT,
    REFILL_12H,
    LESSON_UNLOCK,
    REWARDED_AD,
    STORE_PURCHASE
}

data class CoinTransaction(
    val id: String,
    val amount: Int,
    val type: TransactionType,
    val timestamp: Long = System.currentTimeMillis(),
    val description: String
)

data class Achievement(
    val id: String,
    val titlePt: String,
    val titleEn: String,
    val titleEs: String,
    val descPt: String,
    val descEn: String,
    val descEs: String,
    val iconName: String,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null
) {
    fun getTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.PT -> titlePt
        AppLanguage.EN -> titleEn
        AppLanguage.ES -> titleEs
    }
    fun getDescription(lang: AppLanguage) = when (lang) {
        AppLanguage.PT -> descPt
        AppLanguage.EN -> descEn
        AppLanguage.ES -> descEs
    }
}

data class UserProfile(
    val userId: String = "guest_user",
    val nickname: String = "Estudante Musical",
    val email: String? = null,
    val isGuest: Boolean = true,
    val avatarId: String = "pipo_avatar",
    val coins: Int = 3,
    val lastRefillTimestamp: Long = System.currentTimeMillis(),
    val currentStreak: Int = 1,
    val lastActiveDate: String = "",
    val totalXp: Int = 0,
    val selectedInstrument: InstrumentType = InstrumentType.VIOLAO,
    val language: AppLanguage = AppLanguage.PT,
    val isDarkMode: Boolean = false,
    val soundEffectsEnabled: Boolean = true,
    val hapticFeedbackEnabled: Boolean = true,
    val unlockedLessonIds: Set<String> = emptySet(),
    val completedLessonIds: Set<String> = emptySet(),
    val transactions: List<CoinTransaction> = emptyList()
) {
    companion object {
        const val MAX_FREE_COINS = 3
        const val REFILL_INTERVAL_MILLIS = 12 * 60 * 60 * 1000L // 12 hours = 43,200,000 ms
        const val COIN_PRICE_BRL = 0.10 // R$ 0,10 per coin
    }

    val userLevel: Int get() = (totalXp / 100) + 1
    val xpTowardsNextLevel: Int get() = totalXp % 100

    fun calculateRemainingRefillMillis(now: Long = System.currentTimeMillis()): Long {
        if (coins >= MAX_FREE_COINS) return 0L
        val elapsed = now - lastRefillTimestamp
        val remaining = REFILL_INTERVAL_MILLIS - elapsed
        return if (remaining > 0L) remaining else 0L
    }

    fun isRefillReady(now: Long = System.currentTimeMillis()): Boolean {
        if (coins >= MAX_FREE_COINS) return false
        return (now - lastRefillTimestamp) >= REFILL_INTERVAL_MILLIS
    }
}
