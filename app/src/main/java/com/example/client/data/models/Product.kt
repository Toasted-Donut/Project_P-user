package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = false)
    val name: String?,
    @ColumnInfo(name = "category_ref_id")
    val categoryId: Int?
)