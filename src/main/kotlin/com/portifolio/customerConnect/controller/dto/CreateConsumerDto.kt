package com.portifolio.customerConnect.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class CreateConsumerDto (

    @JsonAlias (value = ["cpf"])
    var cpf: String,

    @JsonAlias (value = ["full_name"])
    var fullName: String,

    @JsonAlias (value = ["full_name"])
    var email : String,

    @JsonAlias (value = ["phone_number"])
    var phoneNumber : String

)
