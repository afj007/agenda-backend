package br.com.estudo

import br.com.estudo.application.plugins.routings.configureRouting
import br.com.estudo.application.plugins.routings.usuarioRouting
import br.com.estudo.infra.config.configureDatabases
import br.com.estudo.infra.repository.Repository
import br.com.estudo.infra.repository.UserRepository
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.ktorm.database.Database

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module, watchPaths = listOf("src/main/resources/application.yaml"))
        .start(wait = true)

}


fun Application.module() {
//    val databases = configureDatabases(environment.config)
    val databases = configureDatabases()
    val userRepository = UserRepository(databases)
    configureRouting()
    usuarioRouting(userRepository)
}

fun Application.configureDatabases(): Database {
    return Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        user = "postgres",
        password = "password"
    )
}