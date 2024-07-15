package com.example.fridgey.models

data class ParsedFood(
    val food: Food
)

data class FoodResponse(
    val text: String,
    val parsed: List<ParsedFood>
)