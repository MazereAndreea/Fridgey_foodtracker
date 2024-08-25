// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

val outputFile = File("$projectDir", "api_secrets.properties")

if (outputFile.exists()) {
    println("API Secrets file already exists in: ${outputFile.absolutePath}")
} else {
    outputFile.parentFile.mkdirs()

    outputFile.writeText(
                "# Get your API Key and APP ID from here:\n" +
                    "# https://developer.edamam.com/food-database-api\n" +
                    "APP_ID = \"your_appid\"\n" +
                    "APP_KEY = \"your_apikey\""
    )
    println("API Secrets file generated in: ${outputFile.absolutePath}")
}