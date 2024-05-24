package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


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
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "categoryId")
    @SerializedName("categoryId")
    val categoryId: Int
)