package br.com.estudo.controllers

import br.com.estudo.model.User
import br.com.estudo.repository.UsuarioRepository
import br.com.estudo.request.UsuarioCreateRequest
import java.util.*

class UserController {

    private val repository = UsuarioRepository()

    fun getAll(): List<User> = repository.getAll()
    fun create(request: UsuarioCreateRequest): User {
        val user = User(
            id = UUID.randomUUID(),
            nome = request.nome,
            email = request.email,
            nickname = request.nickname
        )

        return repository.add(user)
    }

    fun delete(id: UUID): Boolean = repository.delete(id)
}