package br.com.estudo.application.plugins.routings

import br.com.estudo.application.controllers.UserController
import br.com.estudo.application.request.UserCreateRequest
import br.com.estudo.application.request.UserUpdatedRequest
import br.com.estudo.infra.repository.UserRepository
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.logging.Logger

fun Application.usuarioRouting(repository: UserRepository) {

    val userController = UserController(repository)

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        route("usuarios") {
            get {
                val users = userController.getAll()
                this@usuarioRouting.log.info("Listando Usuarios => $users")

                call.respond(HttpStatusCode.OK, mapOf("users" to userController.getAll()))
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                call.respond(HttpStatusCode.OK, userController.get(id))
            }

            post("/") {
                val request = call.receive<UserCreateRequest>()

                call.respond(HttpStatusCode.OK, userController.create(request))
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                call.respond(HttpStatusCode.OK, userController.delete(id))
            }

            put("/{id}") {
                val id = call.parameters["id"] ?: return@put call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                val request = call.receive<UserUpdatedRequest>()
                call.respond(HttpStatusCode.OK, userController.update(id, request))
            }
        }

    }



}