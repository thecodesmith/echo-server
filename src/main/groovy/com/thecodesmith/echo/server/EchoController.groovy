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
    @Consumes([MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.TEXT_PLAIN])
    String post(HttpRequest request, @Body String body, @Header String contentType, String path) {
        def headers = request.headers.asMap()
                .sort { it.key }
                .collect { "Header: $it.key: $it.value" }
                .join('\n')

        def content
        if (contentType == MediaType.APPLICATION_JSON) {
            content = JsonOutput.prettyPrint(body)
        } else if (contentType == MediaType.TEXT_XML) {
            content = XmlUtil.serialize(body)
        } else {
            content = body
        }

        def response = """
                |Request: POST /$path
                |Content-Type: $contentType
                |$headers
                |Body:\n$content
                |""".stripMargin()

        println response
        response
    }
}
