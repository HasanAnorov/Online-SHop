package com.example.onlineshop.networking

import com.example.onlineshop.models.BaseResponse
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.models.TopProductModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("get_offers")
    fun getOffers(): Call<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories():Call<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts():Call<BaseResponse<List<TopProductModel>>>

}