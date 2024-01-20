package br.com.estudo.application.request

data class UsuarioCreateRequest(
    val nome: String,
    val email: String,
    val nickname: String
)