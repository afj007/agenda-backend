package br.com.estudo.infra.repository

import br.com.estudo.domain.model.User
import br.com.estudo.infra.table.UserTable
import io.ktor.server.plugins.*
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.support.postgresql.PostgreSqlDialect
import java.util.UUID

class UserRepository {

    private val dbUsarios = mutableListOf<User>()
    private val database: Database = Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "password",
        dialect = PostgreSqlDialect()
    )

    fun add(user: User): User =
        dbUsarios.add(user).let { if (it) user else throw IllegalArgumentException() }

    fun get(id: UUID): User =
        dbUsarios.firstOrNull { it.id == id } ?: throw NotFoundException("Usuário nao encontrado com o id: $id")

    fun getAll(): List<User> = database.from(UserTable).select().map {
        User(
            id = it[UserTable.id]!!,
            name = it[UserTable.name]!!,
            email = it[UserTable.email]!!,
            nickname = it[UserTable.nickname]!!,
        )
    }

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