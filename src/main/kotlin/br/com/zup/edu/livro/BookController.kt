package br.com.zup.edu.livro

import br.com.zup.edu.author.AuthorRepository
import br.com.zup.edu.categoria.CategoryRepository
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Controller("/books")
@Validated
@Transactional
class BookController {

    @Inject
    lateinit var bookRepository: BookRepository

    @Inject
    lateinit var categoriRepository:CategoryRepository

    @Inject
    lateinit var authorRepository: AuthorRepository

    @Post
    fun create(@Valid @Body request: BookRequest):BookResponse{
        val category = categoriRepository.getById(request.categoryId) ?: throw IllegalArgumentException()

        val author = authorRepository.getById(request.authorId) ?: throw IllegalArgumentException()

        val book = request.toModel(category, author)

        val createdBook =  bookRepository.save(book)

        return BookResponse.modelToDto(createdBook)
    }

    @Get()
    fun getAll(pageable: Pageable): List<BookListResponse>{
       val books = bookRepository.findAll(pageable)
        var booksResponse = mutableListOf<BookListResponse>()

        for (book in books){
            var bookListResponse = BookListResponse.toDto(book)
            booksResponse.add(bookListResponse)
        }

        return booksResponse
    }

}