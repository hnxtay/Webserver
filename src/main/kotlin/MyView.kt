import com.dev.kd1412.httpserver.config.ConfigurationManager
import com.dev.kd1412.httpserver.core.HttpServer
import javafx.geometry.Pos
import tornadofx.*

class MyView : View("Http WebServer") {
    val httpServer = HttpServer()

    init {

    }
    override val root = borderpane() {
        top = form() {
            fieldset {
                hbox {
                    spacing = 12.0
                    alignment = Pos.CENTER
                    field("Address : ") {
                        prefWidth = 200.0
                        label("localhost") {
                        }
                    }

                    hbox {
                        spacing = 12.0
                        button("Start") {
                            action {
                                if (!httpServer.isRunning()){
                                    httpServer.startServer()
                                    text = "Stop"
                                }else{
                                    httpServer.stopServer()
                                    text = "Start"
                                }
                            }
                        }
                        button ("Config"){
                            action {

                            }
                        }
                    }
                }
                hbox {
                    field("Port: ") {
                        prefWidth = 200.0
                        label("8080") {
                        }
                    }
                }
            }
        }
    }
}