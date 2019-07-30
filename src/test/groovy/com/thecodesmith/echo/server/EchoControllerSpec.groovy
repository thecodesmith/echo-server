package com.thecodesmith.echo.server

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
class EchoControllerSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    @AutoCleanup @Inject @Client("/")
    RxHttpClient client

    @Unroll
    def 'test echo #path'() {
        given:
        def response = client.toBlocking().exchange(path, String)

        expect:
        response.status() == HttpStatus.OK
        response.body() == "Path: $path"

        where:
        path << ['/v2/foo/bar']
    }
}
