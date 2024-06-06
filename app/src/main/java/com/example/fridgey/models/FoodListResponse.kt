package com.example.fridgey.models

data class FoodListResponse(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Any>,
    val text: String
)