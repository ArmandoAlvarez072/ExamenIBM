package armando.alvarez.examenibm.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "sale_info")
data class SaleInfo(
    @PrimaryKey(autoGenerate = false)
    var bookId: String,
    @SerializedName("buyLink")
    var buyLink: String?,
    @Ignore
    @SerializedName("listPrice")
    var listPrice: ListPrice?,
    var price: String?
) : Serializable {
    constructor() : this(
        "",
        null,
        null,
        null
    )

    fun getBookPrice(): String {
        return if (price.isNullOrEmpty()) {

            if (listPrice != null)
                listPrice.toString()
            else
                "No Disponible"

        } else {
            price!!
        }
    }
}