package br.com.estudo.application.request

data class UserCreateRequest(
    val name: String,
    val email: String,
    val nickname: String
)
