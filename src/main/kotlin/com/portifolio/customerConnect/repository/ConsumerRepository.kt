package com.portifolio.customerConnect.repository

import com.portifolio.customerConnect.entity.ConsumerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConsumerRepository : JpaRepository<ConsumerEntity, Long> {


    @Query(value = "SELECT * FROM tb_consumers WHERE email = ?1 AND cpf = ?2",
        countQuery = "SELECT COUNT(*) FROM tb_consumers WHERE email = ?1 AND cpf = ?2",
        nativeQuery = true)
    fun findByEmailAndCpf(email: String?, cpf: String?, pageRequest: PageRequest): Page<ConsumerEntity>


    @Query(value = "SELECT * FROM tb_consumers WHERE email = ?1",
        countQuery = "SELECT COUNT(*) FROM tb_consumers WHERE email = ?1",
        nativeQuery = true)
    fun findByEmail(email: String?, pageSize: PageRequest): Page<ConsumerEntity>

    @Query(value = "SELECT * FROM tb_consumers WHERE cpf = ?1",
        countQuery = "SELECT COUNT(*) FROM tb_consumers WHERE cpf = ?1",
        nativeQuery = true)
    fun findByCpf(cpf: String?, pageSize: PageRequest): Page<ConsumerEntity>

    @Query(value = "SELECT * FROM tb_consumers",
        countQuery = "SELECT COUNT(*) FROM tb_consumers",
        nativeQuery = true)
    fun findAll (pagaRequest: PageRequest) : Page<ConsumerEntity>
}