package armando.alvarez.examenibm.data.model


import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("thumbnail")
    var thumbnail: String?
){
    override fun toString(): String {
        return  thumbnail.toString()
    }
}