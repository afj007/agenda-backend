package br.com.estudo.infra.config

import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect

object DataBaseConnect {
    fun connect(): Database = Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "password",
        dialect = PostgreSqlDialect()
    )
}