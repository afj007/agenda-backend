package br.com.estudo.infra.repository

import br.com.estudo.infra.config.DataBaseConnect

abstract class Repository {
    protected val database = DataBaseConnect.connect()
}