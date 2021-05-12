package com.example.onlineshop.networking.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlineshop.models.BaseResponse
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.models.TopProductModel
import com.example.onlineshop.networking.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepository (){
    val compositeDisposable = CompositeDisposable()
    fun getOffers(error:MutableLiveData<String>,progress:MutableLiveData<Boolean>,offers:MutableLiveData<List<OfferModel>>){
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<OfferModel>>>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
                        progress.value = false
                        if (t.success){
                            offers.value = t.data
                        }else{
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        error.value = e.localizedMessage
                    }

                })
        )
    }

    fun getCategories(error:MutableLiveData<String>,categories:MutableLiveData<List<CategoryModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableObserver<BaseResponse<List<CategoryModel>>>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                        if(t.success) {
                            categories.value = t.data
                        }else{
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                })
        )
    }

    fun getTopProducts(error:MutableLiveData<String>,topProducts:MutableLiveData<List<TopProductModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getTopProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableObserver<BaseResponse<List<TopProductModel>>>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<TopProductModel>>) {
                        if(t.success){
                            topProducts.value = t.data
                        }else{
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                })
        )
    }

    fun getCategoryProducts(id:Int, error:MutableLiveData<String>,categoryProducts:MutableLiveData<List<TopProductModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getCategoryProducts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableObserver<BaseResponse<List<TopProductModel>>>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<TopProductModel>>) {
                        if(t.success){
                            categoryProducts.value = t.data
                        }else{
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                })
        )
    }

}