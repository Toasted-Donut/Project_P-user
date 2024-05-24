package com.example.client.data.models

import androidx.room.ColumnInfo

data class CategorySum(
    @ColumnInfo(name = "category_id") val categoryID: Int,
    @ColumnInfo(name = "category_name") val name: String,
    @ColumnInfo(name = "sum") val sum: Float
)