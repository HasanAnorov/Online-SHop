package com.example.onlineshop.networking

import com.example.onlineshop.models.BaseResponse
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.models.TopProductModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories():Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts():Observable<BaseResponse<List<TopProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId:Int):Observable<BaseResponse<List<TopProductModel>>>

}