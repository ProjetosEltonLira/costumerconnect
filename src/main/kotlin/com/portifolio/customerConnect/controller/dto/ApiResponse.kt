package com.portifolio.customerConnect.controller.dto

data class ApiResponse<T>(val data: MutableList<T>,
                          val pagination : PaginationResponse )
