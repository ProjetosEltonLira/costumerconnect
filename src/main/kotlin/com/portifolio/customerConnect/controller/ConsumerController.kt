package com.portifolio.customerConnect.controller

import com.portifolio.customerConnect.controller.dto.ApiResponse
import com.portifolio.customerConnect.controller.dto.CreateConsumerDto
import com.portifolio.customerConnect.controller.dto.PaginationResponse
import com.portifolio.customerConnect.controller.dto.UpdateConsumerrDto
import com.portifolio.customerConnect.entity.ConsumerEntity
import com.portifolio.customerConnect.service.ConsumerService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.URI


@Controller
@RequestMapping(path = ["/customers"])
class ConsumerController (private val consumerService: ConsumerService){

    @PostMapping
    fun createUser(@RequestBody dto: CreateConsumerDto): ResponseEntity<Void> {
        var customer = consumerService.createCustomer(dto)

        return ResponseEntity.created(URI.create("/custumers/${customer?.consumerId}")).build()
    }

    @GetMapping
    fun findAllUsers(@RequestParam(name = "page", defaultValue = "0") page: Int,
                     @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int,
                     @RequestParam(name = "orderBy", defaultValue = "desc") orderBy : String,
                     @RequestParam(name = "email", required = false ) email : String?,
                     @RequestParam(name = "consumer_id", required = false ) consumer_id : String?) : ResponseEntity<ApiResponse<ConsumerEntity>>{

        var pageResponse = consumerService.findAllCustumers(page,pageSize,orderBy,email, consumer_id)

        return ResponseEntity.ok(
            ApiResponse(
                data = pageResponse.content,
                pagination = PaginationResponse(pageResponse.number,pageResponse.size,pageResponse.totalElements,
                    pageResponse.totalPages )
            ))
    }

    @GetMapping (path = ["/{consumer_id}"])
    fun findById(@PathVariable("consumer_id") consumerId: Long) :ResponseEntity<ConsumerEntity>{

        var customer = consumerService.findById(consumerId)

        return if (customer.isPresent) {
            ResponseEntity.of(customer)  //Atualiza, e não retorna body
        }
        else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping (path = ["/{consumer_id}"])
    fun updateUser(@PathVariable("consumer_id") consumerId: Long,
                   @RequestBody dto: UpdateConsumerrDto) :ResponseEntity<ConsumerEntity>{

        var customer = consumerService.updateById(consumerId,dto)

        return if (customer.isPresent) {
            ResponseEntity.noContent().build()  //Atualiza, e não retorna body
        }
        else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping (path = ["/{consumer_id}"])
    fun updateUser(@PathVariable("consumer_id") consumerId: Long) :ResponseEntity<ConsumerEntity>{

        var user = consumerService.deleteById(consumerId)

        return if (user) {
            ResponseEntity.noContent().build()  //Atualiza, e não retorna body
        }
        else {
            ResponseEntity.notFound().build()
        }
    }
}