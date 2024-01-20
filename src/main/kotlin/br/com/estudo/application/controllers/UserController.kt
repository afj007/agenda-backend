package br.com.estudo.application.controllers

import br.com.estudo.domain.model.User
import br.com.estudo.infra.repository.UserRepository
import br.com.estudo.application.request.UsuarioCreateRequest
import java.util.*

class UserController {

    private val repository = UserRepository()

    fun getAll(): List<User> = repository.getAll()
    fun create(request: UsuarioCreateRequest): User {
        val user = User(
            id = UUID.randomUUID(),
            name = request.nome,
            email = request.email,
            nickname = request.nickname
        )

        return repository.add(user)
    }

    fun delete(id: UUID): Boolean = repository.delete(id)
}