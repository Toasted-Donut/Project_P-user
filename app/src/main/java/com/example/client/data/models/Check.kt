package com.example.client.data.models

import androidx.room.*

@Entity
data class Check(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "check_id")
    val id: Long?,

    @ColumnInfo(name = "check_date")
    val date: String?,

    @ColumnInfo(name = "check_filepath")
    val filepath: String?
)
//data class CheckFull(
//    @Embedded
//    val check: Check,
//    @Relation(parentColumn = "check_id", entityColumn = "check_ref_id", entity = CheckItem::class)
//    val items: List<CheckItem>
//)