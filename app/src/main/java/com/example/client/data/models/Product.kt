package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("category_id"),
        childColumns = arrayOf("category_ref_id"),
        onDelete = ForeignKey.SET_NULL
    )]
)
data class Product(
    @PrimaryKey(autoGenerate = false)
    val name: String?,
    @ColumnInfo(name = "category_ref_id")
    val categoryId: Int?
)