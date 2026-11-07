package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
    var showPurchaseDialog by remember { mutableStateOf(false) }
    var selectedPackToBuy by remember { mutableStateOf<BillingManager.ProductPack?>(null) }
    var purchaseSuccessMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loja de Moedas", fontWeight = FontWeight.Bold) },
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

            // Billing Info Banner (Pagamento Único)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.ShoppingBag,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Pagamento Único • Google Play Billing",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = "Sem assinaturas ou cobranças recorrentes. Você paga uma única vez por pacote de moedas.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                                )
                            )
                        }
                    }
                }
            }

            // Fixed Packs Title
            item {
                Text(
                    text = "Escolha um Pacote de Moedas:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Preset Packs
            items(BillingManager.PRESET_PACKS) { pack ->
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("pack_${pack.coinCount}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
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
                                if (pack.subtitle.isNotEmpty()) {
                                    Text(
                                        text = pack.subtitle,
                                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                                    )
                                }
                                Text(
                                    text = "${pack.coinCount} aulas desbloqueadas",
                                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                selectedPackToBuy = pack
                                showPurchaseDialog = true
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("buy_pack_${pack.coinCount}")
                        ) {
                            Text(
                                text = "R$ " + String.format(Locale.US, "%.2f", pack.priceBrl),
                                fontWeight = FontWeight.Bold
                            )
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
        val pack = selectedPackToBuy!!
        AlertDialog(
            onDismissRequest = { showPurchaseDialog = false },
            title = { Text("Confirmar Pagamento Único") },
            text = {
                Column {
                    Text("Você está adquirindo:")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• ${pack.label} (${pack.coinCount} Moedas)", fontWeight = FontWeight.Bold)
                    Text("• Pagamento Único (Google Play In-App Billing)")
                    Text(
                        "• Valor Total: R$ ${String.format(Locale.US, "%.2f", pack.priceBrl)}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Cobrança única via Google Play. Sem renovação automática. As moedas ficam disponíveis imediatamente para você desbloquear suas aulas.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onBuyCoins(pack.coinCount, pack.priceBrl)
                        showPurchaseDialog = false
                        purchaseSuccessMessage = "Compra confirmada! +${pack.coinCount} moedas adicionadas à sua conta."
                    },
                    modifier = Modifier.testTag("confirm_purchase_btn")
                ) {
                    Text("Confirmar Compra")
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
