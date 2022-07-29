package com.example.meliapp.interfaces


import ItemResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {


    //todo Traer solo los atributos que se van a utilizar, pasandolos por paramtro ?atributes=.
    @GET("sites/{siteId}/search")
    suspend fun getItemsPaging(@Path("siteId") id: String?, @Query("q") param1 : String?,
                       @Query("offset") offset : Int?, @Query("limit") limit : Int?) : Response<ItemResponse>

    companion object {

        var BASE_URL = "https://api.mercadolibre.com/"

        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}