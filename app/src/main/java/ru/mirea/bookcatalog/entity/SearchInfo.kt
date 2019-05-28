package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchInfo: Serializable {

    @SerializedName("textSnippet")
    @Expose
    var textSnippet: String? = null

}
