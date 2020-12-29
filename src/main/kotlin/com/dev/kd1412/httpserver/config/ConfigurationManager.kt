package com.dev.kd1412.httpserver.config

import com.google.gson.Gson
import java.io.FileReader

object ConfigurationManager {

    private fun loadConfigurationFile(filePath: String): Configuration {
        return Gson().fromJson(FileReader(filePath), Configuration::class.java)
    }

    private const val filePath = "src/main/resources/https.json"
    val config = loadConfigurationFile(filePath)
}