import com.dev.kd1412.httpserver.core.HttpServer
import tornadofx.*
import kotlin.system.exitProcess

class MyView : View("Http WebServer") {
    val httpServer = HttpServer()
    val processBuilder = ProcessBuilder("gedit", "src/main/resources/https.json")

    init {
    }

    override val root = borderpane() {
        top = form() {
            fieldset {
                hbox {
                    spacing = 12.0
                    field("Address : ") {
                        prefWidth = 200.0
                        label("localhost") {
                        }
                    }

                    hbox {
                        spacing = 12.0
                        button("Start") {
                            action {
                                if (!httpServer.isRunning) {
                                    httpServer.startServer()
                                    text = "Stop"
                                } else {
                                    httpServer.stopServer()
                                    text = "Start"
                                }
                            }
                        }
                        button("Config") {
                            action {
                                processBuilder.start()
                            }
                        }
                    }
                }
                hbox {
                    field("Port: ") {
                        spacing = 12.0
                        prefWidth = 200.0
                        label("8080")
                    }
                }
            }
        }
    }

    override fun onDock() {
        currentWindow?.setOnCloseRequest {
            exitProcess(0)
        }
    }
}