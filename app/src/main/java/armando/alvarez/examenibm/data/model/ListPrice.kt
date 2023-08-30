package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class ListPrice(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("currencyCode")
    var currencyCode: String?
)