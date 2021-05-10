package com.example.onlineshop.models

import android.text.BoringLayout

data class BaseResponse<T> (
    val success : Boolean,
    val data : T,
    val message:String,
    val error_code: Int
)