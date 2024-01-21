package br.com.estudo.application.controllers

import br.com.estudo.application.request.UserCreateRequest
import br.com.estudo.application.request.UserUpdatedRequest
import br.com.estudo.domain.model.User
import br.com.estudo.domain.service.UserService
import br.com.estudo.infra.config.toUUID
import java.util.*

class UserController {

    private val service = UserService()

    fun getAll(): List<User> = service.getAll()

    fun get(id: String): User = service.get(id.toUUID())
    fun create(request: UserCreateRequest): Boolean = service.create(request)
    fun delete(id: String): Boolean = service.delete(id.toUUID())

    fun update(id: String, request: UserUpdatedRequest): Boolean = service.update(id.toUUID(), request)
}
