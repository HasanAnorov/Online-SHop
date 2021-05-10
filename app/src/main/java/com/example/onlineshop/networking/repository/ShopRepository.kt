package com.example.onlineshop.networking.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlineshop.models.BaseResponse
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.models.TopProductModel
import com.example.onlineshop.networking.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepository (){

    fun getOffers(error:MutableLiveData<String>,offers:MutableLiveData<List<OfferModel>>){
        NetworkManager.getApiService().getOffers().enqueue(object : Callback<BaseResponse<List<OfferModel>>> {

            override fun onResponse(call: Call<BaseResponse<List<OfferModel>>>, response: Response<BaseResponse<List<OfferModel>>>) {
                if (response.isSuccessful && response.body()!!.success) {
                    offers.value = response.body()!!.data
                } else {
                    error.value = response.body()?.message ?: response.message()
                }
            }
            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    fun getCategories(error:MutableLiveData<String>,categories:MutableLiveData<List<CategoryModel>>){
        NetworkManager.getApiService().getCategories().enqueue(object : Callback<BaseResponse<List<CategoryModel>>> {
            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }

            override fun onResponse(call: Call<BaseResponse<List<CategoryModel>>>, response: Response<BaseResponse<List<CategoryModel>>>) {
                if (response.isSuccessful && response.body()!!.success) {
                    categories.value = response.body()!!.data
                } else {
                    error.value = response.body()?.message ?: response.message()
                }
            }

        })
    }

    fun getTopProducts(error:MutableLiveData<String>,topProducts:MutableLiveData<List<TopProductModel>>){
        NetworkManager.getApiService().getTopProducts().enqueue(object : Callback<BaseResponse<List<TopProductModel>>> {
            override fun onFailure(call: Call<BaseResponse<List<TopProductModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }

            override fun onResponse(call: Call<BaseResponse<List<TopProductModel>>>, response: Response<BaseResponse<List<TopProductModel>>>) {
                if (response.isSuccessful && response.body()!!.success) {
                    topProducts.value = response.body()!!.data
                }else{
                    error.value = response.body()?.message ?: response.message()
                }
            }

        })
    }

}