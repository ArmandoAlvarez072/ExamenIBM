package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    var id: String?,
    @SerializedName("saleInfo")
    var saleInfo: SaleInfo?,
    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo?
)