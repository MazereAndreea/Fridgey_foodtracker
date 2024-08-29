package com.example.fridgey.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// TODO: Make a DAO ( Data Access Object) for the database
@Entity(tableName = "food_table")
data class TextItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val itemDescription: String,
    val itemImage: ByteArray,
    val expireDate: Date,
    val quantity : Int,
    val category: String
)