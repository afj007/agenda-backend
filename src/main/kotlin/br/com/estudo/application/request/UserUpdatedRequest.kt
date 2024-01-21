package br.com.estudo.application.request

data class UserUpdatedRequest(
    val name: String,
    val email: String,
    val nickname: String
)
