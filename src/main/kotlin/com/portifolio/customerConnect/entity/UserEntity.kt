package com.portifolio.customerConnect.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "tb_users")
data class UserEntity(

    @Id
    @Column(name = "cpf")
    val cpf: String,

    @Column(name = "nome_completo", nullable = false)
    var nomeCompleto: String,

    @Column(name = "email")
    var email : String,

    @Column(name = "telefone")
    var telefone : String,

    @Column(name = "data_registro")
    val dataRegistro : LocalDateTime,

    @Column(name = "data_registro_atualizado")
    val dataRegistroAtualizado : LocalDateTime

)
