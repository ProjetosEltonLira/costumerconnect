package com.portifolio.customerConnect.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class CreateUserDto (

    var cpf: Long,

    @JsonAlias (value = ["full_name"])
    var fullName: String,

    var email : String,

    var phoneNumber : String

)
