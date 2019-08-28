package com.thecodesmith.echo.server

import groovy.json.JsonOutput
import groovy.xml.XmlUtil

import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post

@Controller("/")
class EchoController {

    @Get('{path:.*}')
    String echo(String path) {
        def response = "Request: GET /$path"
        println response
        response
    }

    @Post('{path:.*}')
    @Consumes([MediaType.APPLICATION_JSON, MediaType.TEXT_XML])
    String post(String path, @Body String body, @Header String contentType) {
        def content = contentType == MediaType.APPLICATION_JSON ?
                JsonOutput.prettyPrint(body) :
                XmlUtil.serialize(body)

        def response = """
                |Request: POST /$path
                |Content-Type: $contentType
                |Body: \n$content """.stripMargin()

        println response
        response
    }
}
