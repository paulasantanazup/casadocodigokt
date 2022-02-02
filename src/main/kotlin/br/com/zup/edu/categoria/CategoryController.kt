package br.com.zup.edu.categoria

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.validation.Valid

@Controller("categories")
@Validated
open class CategoryController {

    @Inject
    lateinit var repository:CategoryRepository

    @Post
    fun create(@Valid @Body request:CategoryRequest):HttpResponse<*>{

        if(repository.existsByName(request.name)){
            return HttpResponse.badRequest("Exists category already with the same name")
        }

        val categoryCreated = repository.save(Category(request.name))

        return HttpResponse.ok(CategoryResponse(categoryCreated.id!!, categoryCreated.name))

    }
}