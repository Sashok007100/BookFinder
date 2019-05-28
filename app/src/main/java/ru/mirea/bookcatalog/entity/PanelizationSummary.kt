package ru.mirea.bookcatalog.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PanelizationSummary: Serializable {

    @SerializedName("containsEpubBubbles")
    @Expose
    var containsEpubBubbles: Boolean? = null
    @SerializedName("containsImageBubbles")
    @Expose
    var containsImageBubbles: Boolean? = null

}
