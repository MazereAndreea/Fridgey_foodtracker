package com.example.fridgey.database.groceries

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "groceries_table")
data class ItemGrocery(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val itemDescription: String,
    val itemImage: ByteArray,
    val expireDate: Int /*Date*/,
    val quantity : Int,
    val category: String
)