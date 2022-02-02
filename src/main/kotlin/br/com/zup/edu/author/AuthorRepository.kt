package br.com.zup.edu.author

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {

    fun existsByEmail(email:String): Boolean

    @Query("SELECT * FROM author WHERE id= :id")
    fun getById(id: Long): Author
}