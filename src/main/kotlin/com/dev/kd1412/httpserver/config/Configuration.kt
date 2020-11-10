package com.dev.kd1412.httpserver.config

import com.google.gson.annotations.SerializedName

data class Configuration(var port: Int, var webroot: String? = null)