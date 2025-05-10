package br.com.estudo.infra.config

import io.ktor.server.application.*
import io.ktor.server.config.*
import org.ktorm.database.Database

fun Application.configureDatabases(config: ApplicationConfig): Database {
    val url = config.property("storage.jdbcURL").getString()
    val user = config.property("storage.user").getString()
    val password = config.property("storage.password").getString()

    return Database.connect(
        url,
        user = user,
        password = password
    )
}