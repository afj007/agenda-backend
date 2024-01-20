package br.com.estudo.request

data class UsuarioCreateRequest(
    val nome: String,
    val email: String,
    val nickname: String
)