package com.portifolio.customerConnect.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "tb_consumers")
data class ConsumerEntity(

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    val consumerId: Long,

    @Column(name = "cpf",unique = true)
    val cpf: String,

    @Column(name = "full_name", nullable = false)
    var fullName: String,

    @Column(name = "email", unique = true)
    var email: String,

    @Column(name = "phone_number")
    var phoneNumber: String,

    @CreationTimestamp   // quando for criar, o hibernate cria.
    @Column(name = "created_at")
    val createdAt: LocalDateTime?,

    @UpdateTimestamp    // Quando for atualizar, o hibernate atualiza.
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime?

)
