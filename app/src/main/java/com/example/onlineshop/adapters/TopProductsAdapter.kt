package com.example.onlineshop.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.models.TopProductModel
import com.example.onlineshop.utils.Constants

class TopProductsAdapter(private val topProductsList:List<TopProductModel>):RecyclerView.Adapter<TopProductsAdapter.TopProductsViewHolder>(){

    inner class TopProductsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_top_product,parent,false)
        return TopProductsViewHolder(view)
    }

    override fun getItemCount(): Int = topProductsList.count()

    override fun onBindViewHolder(holder: TopProductsViewHolder, position: Int) {
        val item = topProductsList[position]
        Glide.with(holder.itemView.context).load(Constants.HOST_IMAGE + item.image).into(holder.itemView.findViewById(R.id.topProductsIv))
        holder.itemView.findViewById<TextView>(R.id.productName).text = item.name
        holder.itemView.findViewById<TextView>(R.id.productPrice).text = item.price
    }


}