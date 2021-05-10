package com.example.onlineshop.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.models.CategoryModel

class CategoryAdapter(private val items:List<CategoryModel>) :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_category,parent,false)
        return CategoryViewHolder((view))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            items.forEach{
                it.checked = false
            }
            item.checked = true
            notifyDataSetChanged()
        }

        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = item.title
        if (item.checked){
            holder.itemView.findViewById<CardView>(R.id.cardCategory).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
            holder.itemView.findViewById<TextView>(R.id.tvTitle).setTextColor(Color.WHITE)
        }
        else{
            holder.itemView.findViewById<CardView>(R.id.cardCategory).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
            holder.itemView.findViewById<TextView>(R.id.tvTitle).setTextColor(Color.BLACK)
        }

    }

    override fun getItemCount(): Int = items.count()

}