package pe.edu.idat.sebastiananticona.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WaifusRepository {

    private const val BASE_URL = "https://nekos.best/api/v2/"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiWaifus : ApiWaifus = getRetrofit().create(ApiWaifus::class.java)

}