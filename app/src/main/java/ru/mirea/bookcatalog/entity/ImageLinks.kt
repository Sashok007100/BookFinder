package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageLinks: Serializable {

    @SerializedName("smallThumbnail")
    @Expose
    var smallThumbnail: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

}
