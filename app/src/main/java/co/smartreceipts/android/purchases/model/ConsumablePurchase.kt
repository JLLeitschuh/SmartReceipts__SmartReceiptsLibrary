package co.smartreceipts.android.purchases.model

data class ConsumablePurchase(
    override val inAppPurchase: InAppPurchase,
    override val purchaseData: String,
    override val purchaseToken: String,
    override val inAppDataSignature: String
) : ManagedProduct