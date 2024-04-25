package com.ayoub.jpmcodingexercise.data.network


import com.ayoub.jpmcodingexercise.data.planetResponse
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.test.assertFailsWith

class PlanetApiServiceTest {

    lateinit var server: MockWebServer
    lateinit var api: PlanetApiService

    @Before
    fun setup(){
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .build()
            .create(PlanetApiService::class.java)
    }

    @After
    fun teardown(){
        server.shutdown()
    }

    @Test
    fun test_getPlanets_successful_request() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        val planetResponseString = Json.encodeToString(planetResponse)
        mockResponse.setBody(planetResponseString)
        server.enqueue(mockResponse)
        val response = api.getPlanets(1)
        server.takeRequest()

        Assert.assertEquals(planetResponse, response)
    }

    @Test
    fun test_getPlanets_failed_request() = runTest {
        assertFailsWith<HttpException> {
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(404)
            mockResponse.setBody("{}")
            server.enqueue(mockResponse)
            api.getPlanets(1)
            server.takeRequest()
        }
    }
}
