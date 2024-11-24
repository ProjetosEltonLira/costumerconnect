package com.portifolio.customerConnect.service

import ch.qos.logback.core.util.StringUtil
import com.portifolio.customerConnect.controller.dto.CreateConsumerDto
import com.portifolio.customerConnect.controller.dto.UpdateConsumerrDto
import com.portifolio.customerConnect.entity.ConsumerEntity
import com.portifolio.customerConnect.repository.ConsumerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.Objects.isNull


@Service
class ConsumerService (private val consumerRepository: ConsumerRepository) {

    fun createCustomer(dto: CreateConsumerDto): ConsumerEntity? {
        var consumerEntity = ConsumerEntity(
            consumerId = 0,
            cpf = dto.cpf,
            fullName = dto.fullName,
            email = dto.email,
            phoneNumber = dto.phoneNumber,
            null,
            null
        )
        return consumerRepository.save(consumerEntity)
    }


    fun findAllCustumers(page: Int, pageSize: Int, orderBy: String, email: String?, cpf: String?): Page<ConsumerEntity> {

        var direction = Direction.DESC // Define a direção da ordenação como decrescente
        if (orderBy.equals("asc", ignoreCase = true)) {
            direction = Direction.ASC
        }
        val pageRequest = PageRequest.of(page, pageSize, direction, "created_at")


        if (!isNull(email) and !isNull(cpf)){
            return consumerRepository.findByEmailAndCpf(email,cpf,pageRequest)
        }
        else if (!isNull(email)){
            return consumerRepository.findByEmail(email, pageRequest)
        }
        else if (!isNull(cpf)){
            return consumerRepository.findByCpf(cpf, pageRequest)
        }
        else {
            return consumerRepository.findAll(pageRequest)
        }

    }

    fun updateById(consumerId: Long, dto: UpdateConsumerrDto): Optional<ConsumerEntity?> {

        val consumer = consumerRepository.findById(consumerId)

        if (consumer.isPresent){

            val consumerEntity: ConsumerEntity = consumer.get()

            if(StringUtil.notNullNorEmpty(dto.email)){
                consumerEntity.email = dto.email
            }
            if(StringUtil.notNullNorEmpty(dto.fullName)){
                consumerEntity.fullName = dto.fullName
            }
            if(StringUtil.notNullNorEmpty(dto.phoneNumber)){
                consumerEntity.phoneNumber = dto.phoneNumber
            }

            consumerRepository.save(consumerEntity)

        }
        return consumer
    }

    fun deleteById(consumerId: Long): Boolean {

        var exists = consumerRepository.existsById(consumerId)
        if (exists){
            consumerRepository.deleteById(consumerId)
        }
        return exists



    }

    fun findById(consumerId: Long): Optional<ConsumerEntity> {

        return consumerRepository.findById(consumerId)
    }
}
