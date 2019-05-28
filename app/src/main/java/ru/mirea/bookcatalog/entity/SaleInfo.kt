package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SaleInfo: Serializable {

    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("saleability")
    @Expose
    var saleability: String? = null
    @SerializedName("isEbook")
    @Expose
    var isEbook: Boolean? = null

}
