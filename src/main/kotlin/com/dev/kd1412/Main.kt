package com.dev.kd1412

import com.sun.net.httpserver.HttpServer
import tornadofx.*
import java.io.PrintWriter
import java.net.InetSocketAddress

class Main : App() {

}
fun main(){
    HttpServer.create(InetSocketAddress(8080), 0).apply {

        createContext("/hello") { http ->
            http.responseHeaders.add("Content-type", "text/plain")
            http.sendResponseHeaders(200, 0)
            PrintWriter(http.responseBody).use { out ->
                out.println("<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my Simple Java HTTP Server</h1></body></html>")
            }
        }
        start()
    }
}