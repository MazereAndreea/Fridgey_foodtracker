package com.example.fridgey.database.groceries

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemGrocery::class], version = 1)
abstract class GroceriesClass : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao

    companion object {
        @Volatile
        private var INSTANCE: GroceriesClass? = null

        fun getDatabase(context: Context): GroceriesClass {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceriesClass::class.java,
                    "groceries_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}