package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "product"
//    foreignKeys = [ForeignKey(
//        entity = Category::class,
//        parentColumns = arrayOf("category_id"),
//        childColumns = arrayOf("category_ref_id"),
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Product(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(defaultValue = "product")
    val name: String,
    @ColumnInfo(name = "category_ref_id", index = true)
    val categoryId: Int?
)