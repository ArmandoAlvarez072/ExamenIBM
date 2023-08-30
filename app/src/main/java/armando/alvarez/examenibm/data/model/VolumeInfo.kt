package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("authors")
    var authors: List<String?>?,
    @SerializedName("categories")
    var categories: List<String?>?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("imageLinks")
    var imageLinks: ImageLinks?,
    @SerializedName("pageCount")
    var pageCount: Int?,
    @SerializedName("publishedDate")
    var publishedDate: String?,
    @SerializedName("publisher")
    var publisher: String?,
    @SerializedName("ratingsCount")
    var ratingsCount: Int?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("title")
    var title: String?
)