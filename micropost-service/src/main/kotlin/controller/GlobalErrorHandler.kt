package micropost.core

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateos.JsonError
import org.jooq.exception.DataAccessException

@Controller("/error")
class GlobalErrorHandler {

    @Error(global = true)
    fun error(request: HttpRequest<Any>, ex: RuntimeException): HttpResponse<JsonError> {
        return when (ex) {
            is IllegalArgumentException -> HttpResponse.serverError<JsonError>()
                    .body(JsonError("Http request went wrong: " + ex.message))

            is DataAccessException -> HttpResponse.badRequest<JsonError>()
                    .body(JsonError("Invalid request: " + ex.message))

            else -> HttpResponse.serverError<JsonError>()
                    .body(JsonError("Http request went wrong: " + ex.message))
        }
    }

}