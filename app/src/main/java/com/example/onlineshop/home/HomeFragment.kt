package com.example.onlineshop.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.adapters.CategoryAdapter
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.models.BaseResponse
import com.example.onlineshop.models.CategoryModel
import com.example.onlineshop.models.OfferModel
import com.example.onlineshop.networking.ApiService
import com.synnapps.carouselview.CarouselView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    var offers : List<OfferModel> = emptyList()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var carouselView : CarouselView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.categoryRecyclerVew.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        val rertofit = Retrofit.Builder()
                .baseUrl("http://osonsavdo.sd-group.uz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = rertofit.create(ApiService::class.java)

        api.getOffers().enqueue(object : Callback<BaseResponse<List<OfferModel>>> {

            override fun onResponse(call: Call<BaseResponse<List<OfferModel>>>, response: Response<BaseResponse<List<OfferModel>>>) {
                if (response.isSuccessful && response.body()!!.succes) {
                    offers = response.body()!!.data

                    carouselView = binding.carouselView
                    carouselView.setImageListener { position, imageView ->
                        Glide.with(imageView).load("http://osonsavdo.sd-group.uz/images/${offers[position].image}").into(imageView)
                    }
                    carouselView.pageCount = offers.count()

                } else {
                    Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        api.getCategories().enqueue(object :Callback<BaseResponse<List<CategoryModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<BaseResponse<List<CategoryModel>>>, response: Response<BaseResponse<List<CategoryModel>>>) {
                if (response.isSuccessful && response.body()!!.succes) {
                    binding.categoryRecyclerVew.adapter = CategoryAdapter(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

        return binding.root
    }

}