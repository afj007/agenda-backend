package br.com.estudo.infra.table

import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.text
import org.ktorm.schema.uuid
import java.util.*

object UserTable : Table<Nothing>(tableName = "user", schema = "agenda") {
    val id = uuid("id").primaryKey()
    val name = text("name")
    val email = text("email")
    val nickname = text("nickname")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}