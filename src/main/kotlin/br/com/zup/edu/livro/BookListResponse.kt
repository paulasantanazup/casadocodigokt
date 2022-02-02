package br.com.zup.edu.livro

data class BookListResponse(val id: Long, val title: String) {

    companion object {
        fun toDto(book:Book):BookListResponse{
            return BookListResponse(book.id!!, book.title)
        }
    }
}