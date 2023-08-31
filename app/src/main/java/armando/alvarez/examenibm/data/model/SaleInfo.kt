package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("buyLink")
    var buyLink: String?,
    @SerializedName("listPrice")
    var listPrice: ListPrice?,
)