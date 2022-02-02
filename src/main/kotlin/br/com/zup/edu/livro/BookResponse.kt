package br.com.zup.edu.livro

import br.com.zup.edu.author.Author
import br.com.zup.edu.author.AuthorResponse
import java.math.BigDecimal
import java.time.LocalTime

data class BookResponse(
    val id: Long,
    val title: String,
    val abstract: String,
    val sumary: String,
    val price: BigDecimal,
    val pages: Int,
    val isbn: String,
    val publicationDate: LocalTime,
    val categoryId: Long,
    val authorId: Long,
    val createAt: LocalTime
){

    companion object {
        fun modelToDto(book: Book): BookResponse {
            return BookResponse(
                book.id!!,
                book.title,
                book.abstract,
                book.sumary,
                book.price,
                book.pages,
                book.isbn,
                book.publicationDate,
                book.category.id!!,
                book.author.id!!,
                book.createAt
            )
        }
    }
}
