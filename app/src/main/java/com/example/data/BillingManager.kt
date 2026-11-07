package com.example.data

object BillingManager {
    // Google Play Console Consumable Product IDs (Pagamento Único)
    const val PRODUCT_COINS_10 = "coins_pack_10"
    const val PRODUCT_COINS_25 = "coins_pack_25"
    const val PRODUCT_COINS_50 = "coins_pack_50"
    const val PRODUCT_COINS_100 = "coins_pack_100"

    const val PRICE_PER_COIN_BRL = 0.10

    data class ProductPack(
        val productId: String,
        val coinCount: Int,
        val priceBrl: Double,
        val label: String,
        val subtitle: String = ""
    )

    val PRESET_PACKS = listOf(
        ProductPack(PRODUCT_COINS_10, 10, 1.00, "10 Moedas", "Pacote Básico • Pagamento Único"),
        ProductPack(PRODUCT_COINS_25, 25, 2.50, "25 Moedas", "Pacote Prático • Pagamento Único"),
        ProductPack(PRODUCT_COINS_50, 50, 5.00, "50 Moedas (Mais Popular)", "Mais Escolhido • Pagamento Único"),
        ProductPack(PRODUCT_COINS_100, 100, 10.00, "100 Moedas (Melhor Valor)", "Super Econômico • Pagamento Único")
    )

    fun calculateCustomPrice(quantity: Int): Double {
        val qty = quantity.coerceIn(5, 500)
        return qty * PRICE_PER_COIN_BRL
    }
}
