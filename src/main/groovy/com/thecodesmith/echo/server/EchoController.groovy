package com.thecodesmith.echo.server

import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class EchoController {

    @Get('{path:.*}')
    String echo(HttpRequest request, String path) {
        println "Returning path $request.path"
        "Path: $request.path"
    }
}