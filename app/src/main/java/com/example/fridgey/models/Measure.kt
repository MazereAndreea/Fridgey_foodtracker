package com.example.fridgey.models

data class Measure(
    val label: String,
    val qualified: List<Qualified>,
    val uri: String,
    val weight: Double
)