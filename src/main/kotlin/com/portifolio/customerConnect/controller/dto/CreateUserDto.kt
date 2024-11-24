package com.portifolio.customerConnect.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserDto (

    var cpf: String,

    @JsonAlias (value = ["nome_completo"])
    var nomeCompleto: String,

    var email : String,

    var telefone : String

)
