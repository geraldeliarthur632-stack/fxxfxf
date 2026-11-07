package com.example.data

object AdMobManager {
    // Developer's production AdMob credentials provided for Mundo da Música:
    // Bloco de anúncios: moedas_aulas
    // Formato: Premiado (Rewarded)
    // ID: ca-app-pub-8922902046490534/6230725137
    // Recompensa: 1 moedas para acessar as aulas
    const val PROD_PUBLISHER_ID = "ca-app-pub-8922902046490534"
    const val PROD_REWARDED_AD_UNIT_ID = "ca-app-pub-8922902046490534/6230725137"
    const val AD_UNIT_NAME = "moedas_aulas"
    const val REWARD_ITEM_NAME = "moedas para acessar as aulas"
    const val REWARD_AMOUNT = 1

    // Official Google AdMob Test Unit ID (used as fallback for local emulators/debug to avoid policy strikes)
    const val TEST_APP_ID = "ca-app-pub-3940256099942544~3347511713"
    const val TEST_REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"

    // Set to true to use developer's live AdMob ad unit ID
    var useProductionAdUnit: Boolean = true

    fun getActiveRewardedAdUnitId(): String {
        return if (useProductionAdUnit) PROD_REWARDED_AD_UNIT_ID else TEST_REWARDED_AD_UNIT_ID
    }
}
