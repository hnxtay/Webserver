package com.dev.kd1412.httpserver.core

import com.dev.kd1412.httpserver.config.ConfigurationManager
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket


class HttpServer() {

    var running: Boolean = false

    fun startServer(){
        val CONFIGFILE_PATH = "src/main/resources/https.json"
        val config = ConfigurationManager.loadConfigurationFile(CONFIGFILE_PATH)
        println("Webser using port: ${config.port}")
        println("Webser using webroot: ${config.webroot}")

        try {
            val httpServerThread  = HttpServerThread(config.port,config.webroot)
            httpServerThread.start()
            running =!running
        }catch (e: IOException){ }
    }

    fun stopServer(){
        running = !running
    }

    fun isRunning(): Boolean {
        return running
    }
}