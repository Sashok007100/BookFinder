package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ReadingModes: Serializable {

    @SerializedName("text")
    @Expose
    var text: Boolean? = null
    @SerializedName("image")
    @Expose
    var image: Boolean? = null

}
