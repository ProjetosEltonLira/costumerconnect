package com.portifolio.customerConnect.controller

import com.portifolio.customerConnect.controller.dto.ApiResponse
import com.portifolio.customerConnect.controller.dto.CreateUserDto
import com.portifolio.customerConnect.controller.dto.PaginationResponse
import com.portifolio.customerConnect.controller.dto.UpdateUserDto
import com.portifolio.customerConnect.entity.UserEntity
import com.portifolio.customerConnect.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.Objects.isNull


@Controller
@RequestMapping(path = ["/customers"])
class UserController (private val userService: UserService){

    @PostMapping
    fun createUser(@RequestBody dto: CreateUserDto): ResponseEntity<Void> {
        var user = userService.createUser(dto)

        if (isNull(user))
            return ResponseEntity.badRequest().build()
        else
            return ResponseEntity.created(URI.create("/users/${user?.cpf}")).build()


    }

    @GetMapping
    fun findAllUsers(@RequestParam(name = "page", defaultValue = "0") page: Int,
                     @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int,
                     @RequestParam(name = "orderBy", defaultValue = "desc") orderBy : String,
                     @RequestParam(name = "email", required = false ) email : String?,
                     @RequestParam(name = "cpf", required = false ) cpf : String?) : ResponseEntity<ApiResponse<UserEntity>>{

        var pageResponse = userService.findAllUsers(page,pageSize,orderBy,email, cpf)

        return ResponseEntity.ok(
            ApiResponse(
                data = pageResponse.content,
                pagination = PaginationResponse(pageResponse.number,pageResponse.size,pageResponse.totalElements,
                    pageResponse.totalPages )
            ))
    }


    @PutMapping (path = ["/{cpf}"])
    fun updateUser(@PathVariable("cpf") cpf: Long,
                   @RequestBody dto: UpdateUserDto) :ResponseEntity<UserEntity>{

        var user = userService.updateById(cpf,dto)

        return if (user.isPresent) {
            ResponseEntity.noContent().build()  //Atualiza, e não retorna body
        }
        else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping (path = ["/{cpf}"])
    fun updateUser(@PathVariable("cpf") cpf: Long) :ResponseEntity<UserEntity>{

        var user = userService.deleteById(cpf)

        return if (user) {
            ResponseEntity.noContent().build()  //Atualiza, e não retorna body
        }
        else {
            ResponseEntity.notFound().build()
        }
    }
}