import java.util.Date

data class Transaction(
    val type: String,
    val category: String,
    val account: String,
    val note: String? = null,
    val date: Date,
    val amount: Double,
    val id: Long
)
