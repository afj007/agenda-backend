package br.com.estudo.infra.config

import java.util.UUID

fun String.toUUID(): UUID = UUID.fromString(this)