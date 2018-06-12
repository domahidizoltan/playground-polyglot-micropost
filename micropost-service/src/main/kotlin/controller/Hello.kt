package micropost.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloController {

    @Get("/")
    fun index(): String {
        return "Hello World"
    }

}