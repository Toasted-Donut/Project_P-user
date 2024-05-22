package com.example.client.data.models

import androidx.room.ColumnInfo

data class CategorySum(
    @ColumnInfo(name = "category_ref_id") val categoryID: Int?,
    @ColumnInfo(name = "sum") val sum: Float
)