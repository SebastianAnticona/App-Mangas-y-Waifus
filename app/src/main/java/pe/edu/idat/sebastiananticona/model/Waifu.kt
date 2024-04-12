package pe.edu.idat.sebastiananticona.model

import com.google.gson.annotations.SerializedName

data class Waifu(
    @SerializedName("artist_href")
    val url_pagina_artista: String,
    @SerializedName("artist_name")
    val nombre_artista: String,
    @SerializedName("url")
    val url_imagen: String,
)