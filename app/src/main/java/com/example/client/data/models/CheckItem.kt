package com.example.client.data.models

import androidx.room.*

@Entity(tableName = "check_item",
    primaryKeys = ["check_ref_id", "product_ref_name"],
    foreignKeys = [
//        ForeignKey(
//            entity = Product::class,
//            parentColumns = arrayOf("name"),
//            childColumns = arrayOf("product_ref_name"),
//            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Check::class,
            parentColumns = arrayOf("check_id"),
            childColumns = arrayOf("check_ref_id"),
            onDelete = ForeignKey.CASCADE)
        ]
)
data class CheckItem(
    val sum: Float?,
    @ColumnInfo(name = "check_ref_id", index = true)
    val checkId: Long,
    @ColumnInfo(name = "product_ref_name")
    val productName: String
)