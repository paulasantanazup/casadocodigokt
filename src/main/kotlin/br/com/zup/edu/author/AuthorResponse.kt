package br.com.zup.edu.author

import java.time.LocalTime

data class AuthorResponse(
    val id: Long,
    val name: String,
    val email: String,
    val description: String,
    val create: LocalTime

) {
    companion object {
        fun modelToDto(author: Author): AuthorResponse {
            return AuthorResponse(
                author.id!!,
                author.name,
                author.email,
                author.description,
                author.createAt
            )
        }
    }
}
