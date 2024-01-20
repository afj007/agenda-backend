package br.com.estudo.application.controllers

import br.com.estudo.domain.model.User
import br.com.estudo.infra.repository.UserRepository
import br.com.estudo.application.request.UsuarioCreateRequest
import java.util.*

class UserController {

    private val repository = UserRepository()

    fun getAll(): List<User> = repository.getAll()

    fun get(id: UUID): User = repository.get(id)
    fun create(request: UsuarioCreateRequest): Boolean {
        val user = User(
            id = UUID.randomUUID(),
            name = request.nome,
            email = request.email,
            nickname = request.nickname
        )

        return repository.add(user)
    }

    fun delete(id: UUID): Boolean = repository.delete(id)

    fun update(id: UUID, request: UserUpdatedRequest): Boolean {

    }

}