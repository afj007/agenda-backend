package br.com.estudo.model

import java.util.UUID

data class User(
    val id: UUID,
    val nome: String,
    val email: String,
    val nickname: String
)