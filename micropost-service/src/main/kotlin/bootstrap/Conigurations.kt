package micropost.bootstrap

import com.fasterxml.jackson.databind.ObjectMapper
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
//import micropost.bootstrap.properties.DbConnectionProperties
import micropost.data.tables.daos.MicropostDao
import micropost.data.tables.daos.UserDao
import micropost.service.PostStatisticsClient
import micropost.service.PostStatisticsResponseObserver
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import javax.inject.Singleton
import javax.sql.DataSource
import javax.validation.Validation
import javax.validation.Validator
import org.jooq.Configuration as JooqConfiguration

@Factory
class AppConfiguration {

    @Context @Singleton @Bean
    fun configuration(datasource: DataSource): JooqConfiguration {
        val config = DefaultConfiguration()
        config.setDataSource(datasource)
        config.setSQLDialect(SQLDialect.POSTGRES)
        return config
    }

}

@Factory
class DataConfiguration {

    @Singleton @Bean
    fun userDao(configuration: JooqConfiguration): UserDao = UserDao(configuration)

    @Singleton @Bean
    fun micropostDao(configuration: JooqConfiguration): MicropostDao = MicropostDao(configuration)

    @Singleton @Bean
    fun jooqDsl(configuration: JooqConfiguration): DSLContext = DSL.using(configuration)

    @Singleton @Bean
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator
}

@Factory
class ServiceConfiguration {

    @Singleton @Bean
    fun postStatisticsResponseObserver(jooqDsl: DSLContext, objectMapper: ObjectMapper): PostStatisticsResponseObserver =
            PostStatisticsResponseObserver(jooqDsl, objectMapper)

    @Singleton @Bean
    fun postStatisticsClient(observer: PostStatisticsResponseObserver): PostStatisticsClient =
            PostStatisticsClient("localhost", 8030, observer)

}