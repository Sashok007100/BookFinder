package ru.mirea.bookcatalog.entity.search


import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.mirea.bookcatalog.entity.Book

class SearchResponse {

    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("totalItems")
    @Expose
    var totalItems: Int? = null
    @SerializedName("items")
    @Expose
    var items: ArrayList<Book>? = null

}