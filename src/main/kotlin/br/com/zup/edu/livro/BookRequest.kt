package br.com.zup.edu.livro

import br.com.zup.edu.author.Author
import br.com.zup.edu.categoria.Category
import br.com.zup.edu.commom.Unique
import java.math.BigDecimal
import java.time.LocalTime
import javax.validation.constraints.*

data class BookRequest(
    @field:NotBlank
    @Unique(domainAttribute = "title", domainClass = Book::class)
    val title: String,
    @field:NotBlank
    @field:Size(min = 20)
    val abstract: String,
    val sumary: String,
    val price: BigDecimal,
    @field:NotEmpty
    @Min(value = 100)
    val pages: Int,
    @field:NotBlank
    @Unique(domainAttribute = "isbn", domainClass = Book::class)
    val isbn: String,
    @field:Future
    val publicationDate: LocalTime,
    @field:NotNull
    val categoryId: Long,
    @field:NotNull
    val authorId: Long
) {
    fun toModel(category: Category, author: Author): Book {
        return Book(
            this.title,
            this.abstract,
            this.sumary,
            this.price,
            this.pages,
            this.isbn,
            this.publicationDate,
            category,
            author
        )
    }
}