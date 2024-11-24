package com.portifolio.customerConnect.controller.dto

data class PaginationResponse(val page : Int,
                             val pagaSize : Int,
                             val totalElements : Long,
                             val totalPages : Int)