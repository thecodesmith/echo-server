# Echo Server

_A simple server to debug webhooks, etc._

## Usage

Run the echo server:

    ❯ docker run -p 8080:8080 thecodesmith/echo-server:latest

Send it some requests:

    ❯ curl localhost:8080/hello/world
    Request: GET /hello/world

    ❯ curl localhost:8080/hello/world -X POST -H 'content-type: application/json' -d '{"foo":42}'
    Request: POST /hello/world
    Content-Type: application/json
    Header: Content-Length: [10]
    Header: accept: [*/*]
    Header: content-type: [application/json]
    Header: user-agent: [curl/7.54.0]
    Body:
    {
        "foo": 42
    }

## Deployment

This app is best deployed into some centrally-available infrastructure for
developers to use. See the `manifests` directory for Kubernetes manifest
examples. Change the `ingress.yaml` resource and deploy with:

    kubectl apply -f manifests
