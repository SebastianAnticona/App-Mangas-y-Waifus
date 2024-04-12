package pe.edu.idat.sebastiananticona.model

import com.google.gson.annotations.SerializedName

data class Waifus(
    @SerializedName("results")
    val waifu: List<Waifu>
)