package com.example.onlineshop.models

data class CategoryModel(
        val id : Int,
        val title:String,
        val icon:String,
        var checked:Boolean = true
)