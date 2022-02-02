package br.com.zup.edu.author

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AuthorRequest(

    @field:NotBlank
    var name: String,

    @field:NotBlank
    @Email
    var email: String,

    @field:NotBlank
    @Size(max = 400)
    var description: String
    
){
    fun toModel():Author{
        return Author(name, email, description)
    }
}