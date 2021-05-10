package com.example.onlineshop.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.models.TopProductModel
import com.example.onlineshop.networking.repository.ShopRepository

class MainViewModel () : ViewModel(){

    val repository = ShopRepository()

    val error = MutableLiveData<String>()
    var offerData = MutableLiveData<List<OfferModel>>()
    var categoryData = MutableLiveData<List<CategoryModel>>()
    var topProductsData = MutableLiveData<List<TopProductModel>>()

    fun getOffers(){
        repository.getOffers(error,offerData)
    }

    fun getCategories(){
        repository.getCategories(error,categoryData)
    }

    fun getTopProducts(){
        repository.getTopProducts(error,topProductsData)
    }

}