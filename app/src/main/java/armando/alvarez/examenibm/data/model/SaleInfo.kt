package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("buyLink")
    var buyLink: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("isEbook")
    var isEbook: Boolean?,
    @SerializedName("listPrice")
    var listPrice: ListPrice?,
    @SerializedName("saleability")
    var saleability: String?
)