package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("items")
    var items: List<Item?>?,
    @SerializedName("kind")
    var kind: String?,
    @SerializedName("totalItems")
    var totalItems: Int?
)