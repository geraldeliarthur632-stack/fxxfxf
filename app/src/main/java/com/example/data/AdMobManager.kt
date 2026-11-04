package com.example.data

object AdMobManager {
    // Official Google AdMob Test IDs (safe for development & policy compliance)
    const val TEST_APP_ID = "ca-app-pub-3940256099942544~3347511713"
    const val TEST_REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"

    // When publishing, the developer replaces these with their real AdMob Unit IDs
    var customRewardedAdUnitId: String? = null

    fun getActiveRewardedAdUnitId(): String {
        return customRewardedAdUnitId ?: TEST_REWARDED_AD_UNIT_ID
    }
}
