package com.example.bankuishapp.repository.services


import com.example.bankuishapp.repository.entities.Root
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/repositories")
    suspend fun getItemsPaging(@Query("q") param1 : String?,
                       @Query("page") offset : Int?, @Query("per_page") limit : Int?) : Response<Root>

    companion object {

        var BASE_URL = "https://api.github.com/"

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