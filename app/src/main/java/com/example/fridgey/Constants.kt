package com.example.fridgey

// TODO (STEP 2: Create the activities package and utils package and add the MainActivity class to it and create the constant object in utils.)\
// START
object Constants {
    val APP_ID : String = BuildConfig.APP_ID
    val APP_KEY: String = BuildConfig.APP_KEY
    const val BASE_URL_PARSER: String = "https://api.edamam.com/api/food-database/v2/"
    const val BASE_URL: String = "https://api.edamam.com/"
}
// END