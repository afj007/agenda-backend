package br.com.estudo.application.plugins.routings

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

    routing {
        get("/") {
            System.getenv()
            call.respondText("Hello World!")
        }
    }
}
