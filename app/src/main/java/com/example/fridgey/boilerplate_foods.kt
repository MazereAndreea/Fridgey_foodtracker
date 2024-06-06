package com.example.fridgey

data class boilerplate_foods(
    val _links: Links,
    val hints: List<Hint>,
    val parsed: List<Parsed>,
    val text: String
)