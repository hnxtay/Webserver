package com.dev.kd1412.httpserver.core

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket

class HttpServerThread(val port: Int, val webroot: String?) : Thread(){
    val serverSocket: ServerSocket = ServerSocket(port)

    override fun run() {
        try {
            while (serverSocket.isBound && !serverSocket.isClosed) {
                val socket: Socket = serverSocket.accept()

                println("Connection accepted: ${socket.inetAddress}")

                var input = socket.getInputStream()
                var output = socket.getOutputStream()
                val inputStream: InputStream = File(webroot).inputStream()

                val html = inputStream.bufferedReader().use { it.readText() }
                val CRLF = "\r\n";
                val response = "HTTP/1.1 200 OK" + CRLF+
                        "Content-Length: " + html.length + CRLF +
                        CRLF +
                        html +
                        CRLF + CRLF
                output.write(response.toByteArray())
            }
        } catch (e: IOException) {
            println("An error has occurred with the socket: $e")
        }
    }

}