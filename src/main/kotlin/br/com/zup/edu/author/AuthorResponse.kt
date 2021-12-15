package br.com.zup.edu.author

import java.time.LocalTime

data class AuthorResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val descricao: String,
    val create: LocalTime

) {
    companion object {
        fun modelToDto(author: Author): AuthorResponse {
            return AuthorResponse(
                author.id!!,
                author.nome,
                author.email,
                author.descricao,
                author.create
            )
        }
    }
}
