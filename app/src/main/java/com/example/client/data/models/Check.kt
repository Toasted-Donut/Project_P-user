package com.example.client.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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