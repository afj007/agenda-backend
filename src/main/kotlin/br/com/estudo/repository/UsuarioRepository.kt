package br.com.estudo.repository

import br.com.estudo.model.User
import io.ktor.server.plugins.*
import java.util.UUID

class UsuarioRepository {

    private val dbUsarios = mutableListOf<User>()

    fun add(user: User): User =
        dbUsarios.add(user).let { if (it) user else throw IllegalArgumentException() }

    fun get(id: UUID): User =
        dbUsarios.firstOrNull { it.id == id } ?: throw NotFoundException("Usuário nao encontrado com o id: $id")

    fun getAll(): List<User> = dbUsarios

    fun delete(id: UUID): Boolean {
        val sizeBefore = dbUsarios.size
        dbUsarios.removeAt(dbUsarios.indexOfFirst { it.id == id })

        return sizeBefore > dbUsarios.size
    }

    fun update(user: User): Boolean {
        delete(user.id)
        add(user)

        return dbUsarios.contains(user)
    }
}