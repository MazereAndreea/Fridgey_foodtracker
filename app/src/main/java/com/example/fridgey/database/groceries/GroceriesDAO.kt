package com.example.fridgey.database.groceries

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {
    @Insert
    suspend fun insert(grocery: ItemGrocery)

    @Update
    suspend fun update(grocery: ItemGrocery)

    @Delete
    suspend fun delete(grocery: ItemGrocery)

    @Query("SELECT * FROM groceries_table")
    fun getGroceries(): LiveData<List<ItemGrocery>>
}