package com.example.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.CourseRepository
import com.example.data.UserPreferencesRepository
import com.example.model.AppLanguage
import com.example.model.AppStrings
import com.example.model.InstrumentType
import com.example.ui.screens.*

enum class NavTab {
    HOME,
    PATH,
    STORE,
    ACHIEVEMENTS,
    PROFILE
}

sealed class Screen {
    // Onboarding flow screens (only for new users)
    object LanguageSelect : Screen()
    object Welcome : Screen()
    object Login : Screen()
    object Register : Screen()
    object ForgotPassword : Screen()
    object Nickname : Screen()
    object OnboardingInstrumentSelect : Screen()

    // Main App with Navigation Bar
    data class Main(val activeTab: NavTab = NavTab.HOME) : Screen()

    // Sub-screens (Full focus screens)
    data class LessonView(val lessonId: String) : Screen()
    data class ExerciseView(val lessonId: String) : Screen()
    data class LessonResult(val lessonId: String) : Screen()
    object RewardedAd : Screen()
    object Settings : Screen()
    object HelpSupport : Screen()
    object History : Screen()
    object SwitchInstrument : Screen()
}

@Composable
fun AppNavigation(
    userRepository: UserPreferencesRepository,
    modifier: Modifier = Modifier
) {
    val userProfile by userRepository.userProfile.collectAsState()
    val achievements by userRepository.achievements.collectAsState()

    val isOnboarded = remember { userRepository.hasCompletedOnboarding() }

    var currentScreen by remember {
        mutableStateOf<Screen>(
            if (!isOnboarded) Screen.LanguageSelect else Screen.Main(NavTab.HOME)
        )
    }

    var activeTab by remember { mutableStateOf(NavTab.HOME) }

    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val screen = currentScreen) {
            // STEP 1: Select Language
            is Screen.LanguageSelect -> {
                LanguageSelectionScreen(
                    currentLanguage = userProfile.language,
                    onLanguageSelected = { lang ->
                        userRepository.setLanguage(lang)
                    },
                    onContinue = {
                        navigateTo(Screen.Welcome)
                    }
                )
            }

            // STEP 2: Welcome / Login options
            is Screen.Welcome -> {
                BackHandler {
                    navigateTo(Screen.LanguageSelect)
                }
                WelcomeScreen(
                    language = userProfile.language,
                    onLoginGoogle = { email, name ->
                        userRepository.loginWithGoogleAccount(email, name)
                        if (userRepository.hasCompletedOnboarding()) {
                            navigateTo(Screen.Main(NavTab.HOME))
                        } else {
                            navigateTo(Screen.Nickname)
                        }
                    },
                    onLoginEmail = {
                        navigateTo(Screen.Login)
                    },
                    onCreateAccount = {
                        navigateTo(Screen.Register)
                    },
                    onContinueGuest = {
                        if (userRepository.hasCompletedOnboarding()) {
                            navigateTo(Screen.Main(NavTab.HOME))
                        } else {
                            navigateTo(Screen.Nickname)
                        }
                    }
                )
            }

            is Screen.Login -> {
                BackHandler { navigateTo(Screen.Welcome) }
                LoginScreen(
                    language = userProfile.language,
                    onBack = { navigateTo(Screen.Welcome) },
                    onLoginSuccess = { email ->
                        userRepository.linkAccount(email, email.substringBefore("@"))
                        if (userRepository.hasCompletedOnboarding()) {
                            navigateTo(Screen.Main(NavTab.HOME))
                        } else {
                            navigateTo(Screen.Nickname)
                        }
                    },
                    onLoginGoogle = { email, name ->
                        userRepository.loginWithGoogleAccount(email, name)
                        if (userRepository.hasCompletedOnboarding()) {
                            navigateTo(Screen.Main(NavTab.HOME))
                        } else {
                            navigateTo(Screen.Nickname)
                        }
                    },
                    onForgotPassword = {
                        navigateTo(Screen.ForgotPassword)
                    },
                    onNavigateRegister = {
                        navigateTo(Screen.Register)
                    }
                )
            }

            is Screen.Register -> {
                BackHandler { navigateTo(Screen.Welcome) }
                RegisterScreen(
                    language = userProfile.language,
                    onBack = { navigateTo(Screen.Welcome) },
                    onRegisterSuccess = { email, nick ->
                        userRepository.registerAccount(email, "pass123", nick)
                        navigateTo(Screen.Nickname)
                    },
                    onNavigateLogin = {
                        navigateTo(Screen.Login)
                    }
                )
            }

            is Screen.ForgotPassword -> {
                BackHandler { navigateTo(Screen.Login) }
                ForgotPasswordScreen(
                    language = userProfile.language,
                    onBack = { navigateTo(Screen.Login) }
                )
            }

            // STEP 3: Choose Name and Avatar
            is Screen.Nickname -> {
                BackHandler { navigateTo(Screen.Welcome) }
                NicknameScreen(
                    language = userProfile.language,
                    initialNickname = userProfile.nickname,
                    onConfirm = { nickname, avatar ->
                        userRepository.setNickname(nickname)
                        userRepository.setAvatar(avatar)
                        navigateTo(Screen.OnboardingInstrumentSelect)
                    }
                )
            }

            // STEP 4: Choose Initial Instrument & Complete Onboarding
            is Screen.OnboardingInstrumentSelect -> {
                BackHandler { navigateTo(Screen.Nickname) }
                InstrumentSelectionScreen(
                    language = userProfile.language,
                    currentInstrument = userProfile.selectedInstrument,
                    onSelectInstrument = { inst ->
                        userRepository.setSelectedInstrument(inst)
                        userRepository.completeOnboarding()
                        activeTab = NavTab.HOME
                        navigateTo(Screen.Main(NavTab.HOME))
                    }
                )
            }

            // MAIN APPLICATION SHELL WITH NAVIGATION BAR
            is Screen.Main -> {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("main_bottom_nav_bar")
                        ) {
                            NavigationBarItem(
                                selected = activeTab == NavTab.HOME,
                                onClick = { activeTab = NavTab.HOME },
                                icon = {
                                    Icon(
                                        imageVector = if (activeTab == NavTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                                        contentDescription = AppStrings.get("home_tab", userProfile.language)
                                    )
                                },
                                label = {
                                    Text(
                                        text = AppStrings.get("home_tab", userProfile.language),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = if (activeTab == NavTab.HOME) FontWeight.Bold else FontWeight.Normal)
                                    )
                                },
                                modifier = Modifier.testTag("nav_tab_home")
                            )

                            NavigationBarItem(
                                selected = activeTab == NavTab.PATH,
                                onClick = { activeTab = NavTab.PATH },
                                icon = {
                                    Icon(
                                        imageVector = if (activeTab == NavTab.PATH) Icons.Filled.School else Icons.Outlined.School,
                                        contentDescription = AppStrings.get("learn_path", userProfile.language)
                                    )
                                },
                                label = {
                                    Text(
                                        text = AppStrings.get("learn_path", userProfile.language),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = if (activeTab == NavTab.PATH) FontWeight.Bold else FontWeight.Normal)
                                    )
                                },
                                modifier = Modifier.testTag("nav_tab_path")
                            )

                            NavigationBarItem(
                                selected = activeTab == NavTab.STORE,
                                onClick = { activeTab = NavTab.STORE },
                                icon = {
                                    Icon(
                                        imageVector = if (activeTab == NavTab.STORE) Icons.Filled.Storefront else Icons.Outlined.Storefront,
                                        contentDescription = AppStrings.get("store", userProfile.language)
                                    )
                                },
                                label = {
                                    Text(
                                        text = AppStrings.get("store", userProfile.language),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = if (activeTab == NavTab.STORE) FontWeight.Bold else FontWeight.Normal)
                                    )
                                },
                                modifier = Modifier.testTag("nav_tab_store")
                            )

                            NavigationBarItem(
                                selected = activeTab == NavTab.ACHIEVEMENTS,
                                onClick = { activeTab = NavTab.ACHIEVEMENTS },
                                icon = {
                                    Icon(
                                        imageVector = if (activeTab == NavTab.ACHIEVEMENTS) Icons.Filled.EmojiEvents else Icons.Outlined.EmojiEvents,
                                        contentDescription = AppStrings.get("achievements", userProfile.language)
                                    )
                                },
                                label = {
                                    Text(
                                        text = AppStrings.get("achievements", userProfile.language),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = if (activeTab == NavTab.ACHIEVEMENTS) FontWeight.Bold else FontWeight.Normal)
                                    )
                                },
                                modifier = Modifier.testTag("nav_tab_achievements")
                            )

                            NavigationBarItem(
                                selected = activeTab == NavTab.PROFILE,
                                onClick = { activeTab = NavTab.PROFILE },
                                icon = {
                                    Icon(
                                        imageVector = if (activeTab == NavTab.PROFILE) Icons.Filled.Person else Icons.Outlined.Person,
                                        contentDescription = AppStrings.get("profile", userProfile.language)
                                    )
                                },
                                label = {
                                    Text(
                                        text = AppStrings.get("profile", userProfile.language),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = if (activeTab == NavTab.PROFILE) FontWeight.Bold else FontWeight.Normal)
                                    )
                                },
                                modifier = Modifier.testTag("nav_tab_profile")
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        when (activeTab) {
                            NavTab.HOME -> {
                                HomeScreen(
                                    userProfile = userProfile,
                                    onNavigatePath = { activeTab = NavTab.PATH },
                                    onStartLesson = { lessonId ->
                                        navigateTo(Screen.LessonView(lessonId))
                                    },
                                    onSwitchInstrument = { navigateTo(Screen.SwitchInstrument) },
                                    onNavigateStore = { activeTab = NavTab.STORE },
                                    onNavigateAd = { navigateTo(Screen.RewardedAd) },
                                    onNavigateProfile = { activeTab = NavTab.PROFILE },
                                    onNavigateAchievements = { activeTab = NavTab.ACHIEVEMENTS },
                                    onNavigateHistory = { navigateTo(Screen.History) },
                                    onNavigateSettings = { navigateTo(Screen.Settings) },
                                    onNavigateSupport = { navigateTo(Screen.HelpSupport) }
                                )
                            }
                            NavTab.PATH -> {
                                LearningPathScreen(
                                    userProfile = userProfile,
                                    onBack = { activeTab = NavTab.HOME },
                                    onLessonClick = { lessonId ->
                                        navigateTo(Screen.LessonView(lessonId))
                                    },
                                    onStoreClick = { activeTab = NavTab.STORE },
                                    onWatchAdClick = { navigateTo(Screen.RewardedAd) }
                                )
                            }
                            NavTab.STORE -> {
                                CoinStoreScreen(
                                    userProfile = userProfile,
                                    onBack = { activeTab = NavTab.HOME },
                                    onBuyCoins = { amount, price ->
                                        userRepository.purchaseCoins(amount, price)
                                    },
                                    onWatchAd = { navigateTo(Screen.RewardedAd) }
                                )
                            }
                            NavTab.ACHIEVEMENTS -> {
                                AchievementsScreen(
                                    achievements = achievements,
                                    language = userProfile.language,
                                    onBack = { activeTab = NavTab.HOME }
                                )
                            }
                            NavTab.PROFILE -> {
                                ProfileScreen(
                                    userProfile = userProfile,
                                    onBack = { activeTab = NavTab.HOME },
                                    onUpdateNickname = { newNick ->
                                        userRepository.setNickname(newNick)
                                    },
                                    onLinkAccount = {
                                        navigateTo(Screen.Register)
                                    },
                                    onNavigateAchievements = { activeTab = NavTab.ACHIEVEMENTS },
                                    onNavigateHistory = { navigateTo(Screen.History) }
                                )
                            }
                        }
                    }
                }
            }

            // SUB-SCREENS
            is Screen.SwitchInstrument -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                InstrumentSelectionScreen(
                    language = userProfile.language,
                    currentInstrument = userProfile.selectedInstrument,
                    onSelectInstrument = { inst ->
                        userRepository.setSelectedInstrument(inst)
                        navigateTo(Screen.Main(NavTab.HOME))
                    }
                )
            }

            is Screen.LessonView -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                val lesson = CourseRepository.getLessonById(screen.lessonId)
                if (lesson != null) {
                    LessonScreen(
                        lesson = lesson,
                        userProfile = userProfile,
                        onBack = { navigateTo(Screen.Main(activeTab)) },
                        onStartExercise = {
                            val unlocked = userRepository.unlockOrStartLesson(lesson.id)
                            if (unlocked) {
                                navigateTo(Screen.ExerciseView(lesson.id))
                            } else {
                                activeTab = NavTab.STORE
                                navigateTo(Screen.Main(NavTab.STORE))
                            }
                        },
                        onNeedCoinsForLesson = {
                            activeTab = NavTab.STORE
                            navigateTo(Screen.Main(NavTab.STORE))
                        }
                    )
                } else {
                    navigateTo(Screen.Main(activeTab))
                }
            }

            is Screen.ExerciseView -> {
                BackHandler { navigateTo(Screen.LessonView(screen.lessonId)) }
                val lesson = CourseRepository.getLessonById(screen.lessonId)
                if (lesson != null) {
                    ExerciseScreen(
                        lesson = lesson,
                        userProfile = userProfile,
                        onBack = { navigateTo(Screen.LessonView(screen.lessonId)) },
                        onFinishLesson = {
                            userRepository.completeLesson(lesson.id)
                            navigateTo(Screen.LessonResult(lesson.id))
                        }
                    )
                } else {
                    navigateTo(Screen.Main(activeTab))
                }
            }

            is Screen.LessonResult -> {
                BackHandler {
                    activeTab = NavTab.PATH
                    navigateTo(Screen.Main(NavTab.PATH))
                }
                val lesson = CourseRepository.getLessonById(screen.lessonId)
                if (lesson != null) {
                    LessonResultScreen(
                        lesson = lesson,
                        userProfile = userProfile,
                        onContinue = {
                            activeTab = NavTab.PATH
                            navigateTo(Screen.Main(NavTab.PATH))
                        }
                    )
                } else {
                    activeTab = NavTab.PATH
                    navigateTo(Screen.Main(NavTab.PATH))
                }
            }

            is Screen.RewardedAd -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                RewardedAdScreen(
                    onBack = { navigateTo(Screen.Main(activeTab)) },
                    onRewardEarned = {
                        userRepository.addRewardAdCoin()
                    }
                )
            }

            is Screen.History -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                HistoryScreen(
                    userProfile = userProfile,
                    onBack = { navigateTo(Screen.Main(activeTab)) },
                    onReviewLesson = { lessonId ->
                        navigateTo(Screen.LessonView(lessonId))
                    }
                )
            }

            is Screen.Settings -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                SettingsScreen(
                    userProfile = userProfile,
                    onBack = { navigateTo(Screen.Main(activeTab)) },
                    onLanguageChange = { lang ->
                        userRepository.setLanguage(lang)
                    },
                    onDarkModeToggle = { dark ->
                        userRepository.setDarkMode(dark)
                    },
                    onSoundEffectsToggle = { sound ->
                        userRepository.setSoundEffects(sound)
                    },
                    onHapticsToggle = { haptic ->
                        userRepository.setHapticFeedback(haptic)
                    },
                    onLogout = {
                        userRepository.logout()
                        navigateTo(Screen.Welcome)
                    }
                )
            }

            is Screen.HelpSupport -> {
                BackHandler { navigateTo(Screen.Main(activeTab)) }
                HelpSupportScreen(
                    userProfile = userProfile,
                    onBack = { navigateTo(Screen.Main(activeTab)) }
                )
            }
        }
    }
}
