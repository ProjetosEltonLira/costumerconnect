package com.portifolio.customerConnect.repository

import com.portifolio.customerConnect.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {


    @Query(value = "SELECT * FROM tb_users WHERE email = ?1 AND cpf = ?2",
        countQuery = "SELECT COUNT(*) FROM tb_users WHERE name = ?1 AND cpf = ?2",
        nativeQuery = true)
    fun findByEmailAndCpf(email: String?, cpf: String?, pageRequest: PageRequest): Page<UserEntity>


    @Query(value = "SELECT * FROM tb_users WHERE email = ?1",
        countQuery = "SELECT COUNT(*) FROM tb_users WHERE name = ?1",
        nativeQuery = true)
    fun findByEmail(email: String?, pageSize: PageRequest): Page<UserEntity>

    @Query(value = "SELECT * FROM tb_users WHERE cpf = ?1",
        countQuery = "SELECT COUNT(*) FROM tb_users WHERE cpf = ?1",
        nativeQuery = true)
    fun findByCpf(cpf: String?, pageSize: PageRequest): Page<UserEntity>

    @Query(value = "SELECT * FROM tb_users",
        countQuery = "SELECT COUNT(*) FROM tb_users",
        nativeQuery = true)
    fun findAll (pagaRequest: PageRequest) : Page<UserEntity>
}