package br.com.estudo.infra.plugins

import br.com.estudo.application.controllers.UserController
import br.com.estudo.application.request.UsuarioCreateRequest
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureSerialization() {
    val controler = UserController()


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
                call.respond(HttpStatusCode.OK, mapOf("users" to controler.getAll()))
            }

            post("/") {
                val request = call.receive<UsuarioCreateRequest>()

                call.respond(HttpStatusCode.OK, controler.create(request))
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                call.respond(HttpStatusCode.OK, controler.delete(UUID.fromString(id)))
            }
        }
    }
}
