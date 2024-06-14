package com.example.fridgey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import composables.SimpleDockedSearchBar

class SearchBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val foodList = getFoodList()
            SimpleDockedSearchBar(foodList)
        }
    }
}

