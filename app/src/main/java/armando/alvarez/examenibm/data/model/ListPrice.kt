package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class ListPrice(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("currencyCode")
    var currencyCode: String?
) {
    override fun toString(): String {
        return if (amount == null || currencyCode.isNullOrEmpty()) ""
        else String.format("%.2f", amount) + " $currencyCode"
    }
}