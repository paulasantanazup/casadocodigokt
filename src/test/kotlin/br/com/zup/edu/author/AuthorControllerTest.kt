package br.com.zup.edu.author

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
open class AuthorControllerTest {

    @Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @Test
    fun whenSendNewAuthorShouldCreateAndResponseWithIdAndDateOfCreate() {
        //arrange
        val requestObject = AuthorRequest("Aurelio de Camões", "aurelio10@gmail.com", "Primeiro escritor")
        val request: HttpRequest<Any> = HttpRequest.POST("authors", requestObject)

        //action
        val response = httpClient.toBlocking().exchange(request, AuthorResponse::class.java)

        //assert:
        assertEquals(HttpStatus.CREATED, response.status)
        assertNotNull(response.body())
        assertNotNull(response.body().id)
        assertNotNull(response.body().create)
        assertEquals(response.body().name, requestObject.name)
        assertEquals(response.body().email, requestObject.email)
        assertEquals(response.body().description, requestObject.description)
    }

    @Test
    fun whenSendNewAuthorThatAlreadyExistsShouldReturnMessageAndStatusBadRequest() {
        //arrange
        val requestObject = AuthorRequest("Aurelio de Camões", "aurelio10@gmail.com", "Primeiro escritor")

        val request: HttpRequest<Any> = HttpRequest.POST("authors", requestObject)

        //action
        httpClient.toBlocking().exchange(request, AuthorResponse::class.java)

        val responseCreateAgain = httpClient.toBlocking().exchange(request, String::class.java)

        //assert:
        assertEquals(HttpStatus.BAD_REQUEST, responseCreateAgain.status)
        assertNotNull(responseCreateAgain.body())
    }

}