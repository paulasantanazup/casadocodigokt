package br.com.zup.edu.categoria

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {

    fun existsByName(name:String): Boolean

    @Query("SELECT * FROM category WHERE id= :id")
    fun getById(id:Long): Category

}