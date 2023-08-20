package com.abhi165.moviemania.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.themoviedb.org"
private const val KEY = "PASTE_YOUR_API_KEY_HERE"

 val client = HttpClient {
     expectSuccess = true
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            useAlternativeNames = true
        })
    }

    install(DefaultRequest) {
        url(BASE_URL)
        header(HttpHeaders.Accept, ContentType.Application.Json)
        header("Authorization", "Bearer $KEY")
    }

     install(Logging) {
         level = LogLevel.ALL
         logger = Test()
     }
}

class Test: Logger {
    override fun log(message: String) {
        print(message)
    }
}