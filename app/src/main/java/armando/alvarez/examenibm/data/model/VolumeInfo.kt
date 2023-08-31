package armando.alvarez.examenibm.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "volume_info")
data class VolumeInfo(
    @Ignore
    @SerializedName("authors")
    var authors: List<String?>?,
    @SerializedName("description")
    var description: String?,
    @Ignore
    @SerializedName("imageLinks")
    var imageLinks: ImageLinks?,
    @SerializedName("publishedDate")
    var publishedDate: String?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("title")
    var title: String?,
    @PrimaryKey(autoGenerate = false)
    var bookId: String,
    var author: String?,
    var image: String?
) : Serializable {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        "",
        null,
        null
    )

    fun getAuthors(): String {

        return if (this.author.isNullOrEmpty()) {
            var author = ""
            authors?.forEach {
                author += "$it, "
            }

            if (author.isNotEmpty()) author.substring(0, author.length - 2) else author
        } else {
            this.author!!
        }

    }

    fun getImageLink(): String {
        return if (this.image.isNullOrEmpty()) {
            imageLinks.toString()
        } else {
            this.image!!
        }

    }
}