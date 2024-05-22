package com.example.client.data.models

import androidx.room.*

@Entity(tableName = "check_item",
foreignKeys = [
    ForeignKey(
        entity = Product::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("product_ref_name"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = Check::class,
        parentColumns = arrayOf("check_id"),
        childColumns = arrayOf("check_ref_id"),
        onDelete = ForeignKey.CASCADE)
    ]
)
data class CheckItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "check_item_id")
    val id: Long?,
    val sum: Float?,
    @ColumnInfo(name = "check_ref_id")
    val checkId: Long?,
    @ColumnInfo(name = "product_ref_name")
    val productName: String?
)

data class CheckItemFull(
    @Embedded
    val checkItem: CheckItem,
    @Relation(parentColumn = "product_ref_name", entityColumn = "name", entity = Product::class)
    val product: Set<Product>
)