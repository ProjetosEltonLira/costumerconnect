package com.portifolio.customerConnect.service

import com.portifolio.customerConnect.controller.dto.CreateUserDto
import com.portifolio.customerConnect.entity.UserEntity
import com.portifolio.customerConnect.repository.UserRepositoy
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UserService (private val userRepositoy: UserRepositoy) {

    fun createUser(dto: CreateUserDto): UserEntity {
        var userEntity = UserEntity(
            cpf = dto.cpf,
            nomeCompleto = dto.nomeCompleto,
            email = dto.email,
            telefone = dto.telefone,
            dataRegistro = LocalDateTime.now(),
            dataRegistroAtualizado = LocalDateTime.now()
        )
        return userRepositoy.save(userEntity)
    }
}
