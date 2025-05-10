package br.com.estudo.infra.repository

import br.com.estudo.domain.model.User
import br.com.estudo.infra.table.UserTable
import org.ktorm.database.Database
import org.ktorm.dsl.Query
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.dsl.update
import org.ktorm.dsl.where
import java.time.LocalDateTime
import java.util.*

class UserRepository(private val database: Database): Repository {

    fun add(user: User): Boolean = database.insert(UserTable) {
        set(UserTable.id, user.id)
        set(UserTable.name, user.name)
        set(UserTable.email, user.email)
        set(UserTable.nickname, user.nickname)
        set(UserTable.createdAt, user.createdAt)
        set(UserTable.updatedAt, user.updatedAt)
    } > 0

    fun get(id: UUID): User =
        database.from(UserTable).select().where {
            UserTable.id eq id
        }.buildUser().first()

    fun getAll(): List<User> = database.from(UserTable).select().buildUser()

    fun delete(id: UUID): Boolean = database.delete(UserTable) { it.id eq id } > 0

    fun update(user: User): Boolean = database.update(UserTable) {
        set(UserTable.name, user.name)
        set(UserTable.email, user.email)
        set(UserTable.nickname, user.nickname)
        set(UserTable.createdAt, user.createdAt)
        set(UserTable.updatedAt, user.updatedAt)
        where {
            UserTable.id eq user.id
        }
    } > 0

    private fun QueryRowSet.buildUser() = User(
        id = this[UserTable.id]!!,
        name = this[UserTable.name]!!,
        email = this[UserTable.email]!!,
        nickname = this[UserTable.nickname]!!,
        createdAt = this[UserTable.createdAt] ?: LocalDateTime.now()
    )

    private fun Query.buildUser(): List<User> = this.map {
        it.buildUser()
    }
}
