package com.example.onlineshop.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshop.ViewModel.MainViewModel
import com.example.onlineshop.adapters.CategoryAdapter
import com.example.onlineshop.adapters.TopProductsAdapter
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.synnapps.carouselview.CarouselView


class HomeFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var carouselView : CarouselView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.categoryRecyclerVew.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })
        viewModel.offerData.observe(requireActivity(), Observer {
                                carouselView = binding.carouselView
                    carouselView.setImageListener { position, imageView ->
                        Glide.with(imageView).load("http://osonsavdo.sd-group.uz/images/${it[position].image}").into(imageView)
                    }
                    carouselView.pageCount = it.count()
        })

        viewModel.categoryData.observe(requireActivity(), Observer {
            binding.categoryRecyclerVew.adapter = CategoryAdapter(it ?: emptyList())
        })

        viewModel.topProductsData.observe(requireActivity(), Observer {
            binding.topProductsRecyclerView.adapter = TopProductsAdapter(it)
        })

        loadData()

        return binding.root
    }

    fun loadData(){
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getTopProducts()
    }

}