package com.dev.kd1412.httpserver.core

import com.dev.kd1412.httpserver.config.ConfigurationManager.config
import com.sun.net.httpserver.HttpServer
import java.io.File
import java.net.InetSocketAddress
import java.nio.charset.Charset

typealias JHttpServer = HttpServer

class HttpServer {
    private lateinit var server: HttpServer

    fun startServer() {
        server = JHttpServer.create(InetSocketAddress(8080), 0)
        with(server) {
            createContext("/") { t ->
                val response = File("${config.webroot!!}index.html").readBytes()
                t.sendResponseHeaders(200, response.size.toLong())
                val os = t.responseBody
                os.write(response)
                os.close()
            }
            createContext("/login") { t ->
                val os = t.responseBody
                if (t.requestMethod == "POST") {
                    val body = t.requestBody.readBytes().toString(Charset.defaultCharset()).split("&").map {
                        val list = it.split("=")
                        list[0] to list[1]
                    }.toMap()

                    if (body["username"] == "admin" && body["password"] == "123") {
                        val response = "".toByteArray()
                        t.sendResponseHeaders(200, response.size.toLong())
                        os.write(response)
                    }
                } else {
                    val response = "cut".toByteArray()
                    t.sendResponseHeaders(200, response.size.toLong())
                    os.write(response)
                }
                os.close()
            }
            createContext("/" +
                    "") { t ->
                val response = File("${config.webroot!!}success.html").readBytes()
                t.sendResponseHeaders(200, response.size.toLong())
                val os = t.responseBody
                os.write(response)
                os.close()
            }
        }
        server.executor = null // creates a default executor

        server.start()
        println("Server is running on http://localhost:${config.port} ")
        isRunning = true
        Runtime.getRuntime().exec(arrayOf("chromium-browser", "http://localhost:${config.port}"))
    }

    var isRunning = false
        private set

    fun stopServer() = server.stop(0).also {
        isRunning = false
        println("Server is closed...")
    }
}