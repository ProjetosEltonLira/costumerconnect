package com.portifolio.customerConnect.service

import ch.qos.logback.core.util.StringUtil
import com.portifolio.customerConnect.controller.dto.CreateUserDto
import com.portifolio.customerConnect.controller.dto.UpdateUserDto
import com.portifolio.customerConnect.entity.UserEntity
import com.portifolio.customerConnect.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.Objects.isNull


@Service
class UserService (private val userRepository: UserRepository) {

    fun createUser(dto: CreateUserDto): UserEntity? {
        var userEntity = UserEntity(
            cpf = dto.cpf,
            fullName = dto.fullName,
            email = dto.email,
            phoneNumber = dto.phoneNumber,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        if (!userRepository.existsById(dto.cpf))
            return userRepository.save(userEntity)

        return null
    }


    fun findAllUsers(page: Int, pageSize: Int, orderBy: String, email: String?, cpf: String?): Page<UserEntity> {

        var direction = Sort.Direction.DESC // Define a direção da ordenação como decrescente
        if (orderBy.equals("asc", ignoreCase = true)) {
            direction = Sort.Direction.ASC
        }
        val pageRequest = PageRequest.of(page, pageSize, direction, "created_at")


        if (!isNull(email) and !isNull(cpf)){
            return userRepository.findByEmailAndCpf(email,cpf,pageRequest)
        }
        else if (!isNull(email)){
            return userRepository.findByEmail(email, pageRequest)
        }
        else if (!isNull(cpf)){
            return userRepository.findByCpf(cpf, pageRequest)
        }
        else {
            return userRepository.findAll(pageRequest)
        }

    }

    fun updateById(cpf: Long, dto: UpdateUserDto): Optional<UserEntity?> {

        val user = userRepository.findById(cpf)

        if (user.isPresent){

            val userEntity: UserEntity = user.get()

            if(StringUtil.notNullNorEmpty(dto.email)){
                userEntity.email = dto.email
            }
            if(StringUtil.notNullNorEmpty(dto.fullName)){
                userEntity.fullName = dto.fullName
            }
            if(StringUtil.notNullNorEmpty(dto.phoneNumber)){
                userEntity.phoneNumber = dto.phoneNumber
            }
            userEntity.updatedAt = LocalDateTime.now()
            userRepository.save(userEntity)

        }
        return user
    }

    fun deleteById(cpf: Long): Boolean {

        var exists = userRepository.existsById(cpf)
        if (exists){
            userRepository.deleteById(cpf)
        }
        return exists



    }
}
