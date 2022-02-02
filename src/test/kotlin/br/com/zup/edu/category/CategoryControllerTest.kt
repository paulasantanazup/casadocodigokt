package br.com.zup.edu.category

import br.com.zup.edu.categoria.CategoryRequest
import br.com.zup.edu.categoria.CategoryResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test


@MicronautTest
open class CategoryControllerTest {

    @Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @Test
    fun whenSendNewCategoryShouldCreateAndResponseWithId() {
        //arrange
        val requestObject = CategoryRequest("Drama")
        val request: HttpRequest<Any> = HttpRequest.POST("categories", requestObject)

        //action
        val response = httpClient.toBlocking().exchange(request, CategoryResponse::class.java)

        //assert:
        assertEquals(HttpStatus.CREATED, response.status)
        assertNotNull(response.body())
        assertNotNull(response.body().id)
        assertEquals(response.body().name, requestObject.name)

    }

}