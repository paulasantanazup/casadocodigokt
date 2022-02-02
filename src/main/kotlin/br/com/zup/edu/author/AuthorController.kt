package br.com.zup.edu.author

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Controller("/authors")
@Validated
@Transactional
class AuthorController {

    @Inject
    lateinit var repository: AuthorRepository;

    @Post
    fun create(@Valid @Body request: AuthorRequest): HttpResponse<*> {

        if (repository.existsByEmail(request.email)) {
            return HttpResponse.badRequest("Exists author already with the same e-mail")
        }

        var authorCreated = repository.save(request.toModel())

        return HttpResponse.created(AuthorResponse.modelToDto(authorCreated));
    }
}