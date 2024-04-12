package pe.edu.idat.sebastiananticona.retrofit

import pe.edu.idat.sebastiananticona.model.Waifus
import retrofit2.http.GET

interface ApiWaifus {

    @GET("waifu?amount=20")
    suspend fun list_Waifus() : Waifus
}