package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Epub: Serializable {

    @SerializedName("isAvailable")
    @Expose
    var isAvailable: Boolean? = null

}
