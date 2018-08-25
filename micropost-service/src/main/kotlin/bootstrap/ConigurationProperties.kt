package micropost.bootstrap.properties

import io.micronaut.context.annotation.ConfigurationProperties
import javax.validation.constraints.NotBlank

@ConfigurationProperties("datasources.default")
data class DbConnectionProperties(@NotBlank val driverClassName: String,
                        @NotBlank val url: String,
                        @NotBlank val user: String,
                        val password: String)
