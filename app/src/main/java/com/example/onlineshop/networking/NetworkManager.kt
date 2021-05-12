package com.example.onlineshop.networking

import com.example.onlineshop.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    var retofit : Retrofit? = null
    var api : ApiService? = null

    fun getApiService() : ApiService {
        if (api==null){
            retofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            api = retofit!!.create(ApiService::class.java)

        }
        return api!!
    }

}