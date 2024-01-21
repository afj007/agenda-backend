package br.com.estudo.application.plugins

import br.com.estudo.application.controllers.UserController
import br.com.estudo.application.request.UserUpdatedRequest
import br.com.estudo.application.request.UserCreateRequest
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    val userController = UserController()


    install(ContentNegotiation) {
        gson {
        }
    }
    routing {
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        route("usuarios") {
            get("/") {
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
