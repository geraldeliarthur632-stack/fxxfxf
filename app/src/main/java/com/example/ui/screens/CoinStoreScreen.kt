package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BillingManager
import com.example.model.CoinTransaction
import com.example.model.TransactionType
import com.example.model.UserProfile
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinStoreScreen(
    userProfile: UserProfile,
    onBack: () -> Unit,
    onBuyCoins: (amount: Int, totalPriceBrl: Double) -> Unit,
    onWatchAd: () -> Unit
) {
    var customQuantityText by remember { mutableStateOf("20") }
    var showPurchaseDialog by remember { mutableStateOf(false) }
    var selectedPackToBuy by remember { mutableStateOf<Pair<Int, Double>?>(null) }
    var purchaseSuccessMessage by remember { mutableStateOf<String?>(null) }

    val customQty = customQuantityText.toIntOrNull()?.coerceIn(1, 1000) ?: 10
    val customPrice = BillingManager.calculateCustomPrice(customQty)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loja de Notas Musicais", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("store_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Current Balance & Info
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Saldo Atual de Moedas:",
                                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF92400E))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFF59E0B), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.MusicNote, contentDescription = null, tint = Color.White)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${userProfile.coins} Moedas",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF78350F)
                                    )
                                )
                            }
                        }

                        FilledTonalButton(
                            onClick = onWatchAd,
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = Color(0xFFF59E0B),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.testTag("store_free_ad_btn")
                        ) {
                            Icon(Icons.Default.PlayCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Grátis (+1)")
                        }
                    }
                }
            }

            if (purchaseSuccessMessage != null) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFECFDF5)),
                        border = BorderStroke(1.5.dp, Color(0xFF10B981))
                    ) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF059669))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(purchaseSuccessMessage!!, color = Color(0xFF065F46), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Fixed Packs Title
            item {
                Text(
                    text = "Pacotes Prontos (R$ 0,10 por moeda):",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Preset Packs
            items(BillingManager.PRESET_PACKS) { pack ->
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("pack_${pack.coinCount}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(pack.label, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                                Text(
                                    text = "${pack.coinCount} aulas desbloqueadas",
                                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                selectedPackToBuy = Pair(pack.coinCount, pack.priceBrl)
                                showPurchaseDialog = true
                            },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "R$ " + String.format(Locale.US, "%.2f", pack.priceBrl),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Custom Quantity Calculator
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("custom_coin_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Quantidade Personalizada:",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Digite exatamente quantas moedas deseja comprar. O valor total é calculado automaticamente.",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = customQuantityText,
                            onValueChange = { customQuantityText = it.filter { char -> char.isDigit() } },
                            label = { Text("Quantidade de Moedas") },
                            leadingIcon = { Icon(Icons.Default.MusicNote, contentDescription = null) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().testTag("custom_coin_input"),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Dynamic calculation summary
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Quantidade:", style = MaterialTheme.typography.bodyMedium)
                                    Text("$customQty moedas", fontWeight = FontWeight.Bold)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Valor unitário:", style = MaterialTheme.typography.bodyMedium)
                                    Text("R$ 0,10 / un", fontWeight = FontWeight.Medium)
                                }
                                HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Valor Total:", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                                    Text(
                                        text = "R$ " + String.format(Locale.US, "%.2f", customPrice),
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                selectedPackToBuy = Pair(customQty, customPrice)
                                showPurchaseDialog = true
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("custom_buy_btn"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Comprar $customQty Moedas", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Transaction History
            item {
                Text(
                    text = "Histórico de Movimentações:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            val reversedTransactions = userProfile.transactions.reversed()
            if (reversedTransactions.isEmpty()) {
                item {
                    Text(
                        text = "Nenhuma movimentação registrada ainda.",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
            } else {
                items(reversedTransactions) { trans ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = trans.description,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                                )
                                val dateStr = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(trans.timestamp))
                                Text(
                                    text = dateStr,
                                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.outline)
                                )
                            }
                            Text(
                                text = if (trans.amount > 0) "+${trans.amount}" else "${trans.amount}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (trans.amount > 0) Color(0xFF059669) else Color(0xFFDC2626)
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    if (showPurchaseDialog && selectedPackToBuy != null) {
        val (qty, price) = selectedPackToBuy!!
        AlertDialog(
            onDismissRequest = { showPurchaseDialog = false },
            title = { Text("Confirmar Compra") },
            text = {
                Column {
                    Text("Você está adquirindo:")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• $qty Notas Musicais (Moedas)", fontWeight = FontWeight.Bold)
                    Text("• Valor Unitário: R$ 0,10")
                    Text("• Valor Total: R$ ${String.format(Locale.US, "%.2f", price)}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Integração Google Play Billing pronta para produção. O crédito das moedas será registrado no histórico.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onBuyCoins(qty, price)
                        showPurchaseDialog = false
                        purchaseSuccessMessage = "Compra confirmada! +$qty moedas adicionadas à sua conta."
                    },
                    modifier = Modifier.testTag("confirm_purchase_btn")
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPurchaseDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
