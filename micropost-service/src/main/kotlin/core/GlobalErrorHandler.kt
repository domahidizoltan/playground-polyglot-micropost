package micropost.core

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateos.JsonError

@Controller
class GlobalErrorHandler {

    @Error(global = true)
    fun error(request: HttpRequest<Any>, ex: RuntimeException): HttpResponse<JsonError> {
        val jsonError = JsonError("Http request went wrong: " + ex.message)
        return HttpResponse.serverError<JsonError>().body(jsonError)
    }

}