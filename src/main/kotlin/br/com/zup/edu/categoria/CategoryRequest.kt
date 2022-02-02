package br.com.zup.edu.categoria

import javax.validation.constraints.NotBlank

data class CategoryRequest(
    @NotBlank
    val name: String
)
