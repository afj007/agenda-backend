package br.com.estudo.infra.repository

import br.com.estudo.domain.model.User
import br.com.estudo.infra.config.DataBaseConnect
import br.com.estudo.infra.table.UserTable
import org.ktorm.dsl.*
import java.util.UUID

class UserRepository: Repository() {

    fun add(user: User): Boolean = database.insert(UserTable) {
        set(UserTable.id, user.id)
        set(UserTable.name, user.name)
        set(UserTable.email, user.email)
        set(UserTable.nickname, user.nickname)
    } > 0

    fun get(id: UUID): User {
        return database.from(UserTable).select().where {
            UserTable.id eq id
        }.buildUser().first()
    }

    fun getAll(): List<User> = database.from(UserTable).select().buildUser()

    fun delete(id: UUID): Boolean = database.delete(UserTable) { it.id eq id } > 0

    fun update(user: User): Boolean = database.update(UserTable) {
        set(UserTable.name, user.name)
        set(UserTable.email, user.email)
        set(UserTable.nickname, user.nickname)
        where {
            UserTable.id eq user.id
        }
    } > 0

    private fun QueryRowSet.buildUser() = User(
        id = this[UserTable.id]!!,
        name = this[UserTable.name]!!,
        email = this[UserTable.email]!!,
        nickname = this[UserTable.nickname]!!,
    )

    private fun Query.buildUser(): List<User> = this.map {
        it.buildUser()
    }
}
