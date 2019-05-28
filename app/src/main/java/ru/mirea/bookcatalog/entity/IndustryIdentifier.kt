package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class IndustryIdentifier: Serializable {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

}
