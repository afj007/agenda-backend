package br.com.estudo.domain.service

import br.com.estudo.application.request.UserCreateRequest
import br.com.estudo.application.request.UserUpdatedRequest
import br.com.estudo.domain.model.User
import br.com.estudo.infra.repository.UserRepository
import java.util.*

class UserService {

    private val repository = UserRepository()

    fun getAll(): List<User> = repository.getAll()

    fun get(id: UUID): User = repository.get(id)
    fun create(request: UserCreateRequest): Boolean =
        repository.add(
            User(
                id = UUID.randomUUID(),
                name = request.name,
                email = request.email,
                nickname = request.nickname
            )
        )
    fun delete(id: UUID): Boolean = repository.delete(id)

    fun update(id: UUID, request: UserUpdatedRequest): Boolean =
        repository.update(
            User(
                id = id,
                name = request.name,
                email = request.email,
                nickname = request.nickname
            )
        )
}
