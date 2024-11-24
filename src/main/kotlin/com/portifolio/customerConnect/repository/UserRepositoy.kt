package com.portifolio.customerConnect.repository

import com.portifolio.customerConnect.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositoy : JpaRepository<UserEntity, Long> {
}