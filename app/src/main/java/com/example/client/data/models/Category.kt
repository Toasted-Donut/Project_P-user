package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "category_id")
    val id: Int,
    @ColumnInfo(name = "category_name")
    val name: String
)