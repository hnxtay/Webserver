package com.dev.kd1412.httpserver.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileReader

object ConfigurationManager {

    fun loadConfigurationFile(filePath: String): Configuration {
        val configuration :Configuration = Gson().fromJson(FileReader(filePath),Configuration::class.java)
        return configuration
    }
}