package com.portifolio.customerConnect.controller

import com.portifolio.customerConnect.controller.dto.CreateUserDto
import com.portifolio.customerConnect.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI


@Controller
@RequestMapping(path = ["/customers"])
class UserController (private val userService: UserService){

    @PostMapping
    fun createUser(@RequestBody dto: CreateUserDto): ResponseEntity<Void> {
        var user = userService.createUser(dto)
        return ResponseEntity.created(URI.create("/users/${user.cpf}")).build()
    }
}