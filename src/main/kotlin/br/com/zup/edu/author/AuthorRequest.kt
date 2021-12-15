package br.com.zup.edu.author

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AuthorRequest(

    @NotBlank
    var nome: String,

    @NotBlank
    @Email
    var email: String,

    @NotBlank
    @Size(max = 400)
    var descricao: String
    
){
    fun toModel():Author{
        return Author(nome, email, descricao)
    }
}