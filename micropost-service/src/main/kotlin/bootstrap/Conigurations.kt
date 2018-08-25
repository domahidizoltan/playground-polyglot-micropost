package micropost.bootstrap

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import micropost.bootstrap.properties.DbConnectionProperties
import micropost.data.tables.daos.MicropostDao
import micropost.data.tables.daos.UserDao
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

    @Bean
    @Singleton
    fun datasource(db: DbConnectionProperties): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = db.url
        config.username = db.user
        config.password = db.password
        config.driverClassName = db.driverClassName
        return HikariDataSource(config)
    }

    @Bean
    @Singleton
    fun configuration(datasource: DataSource): JooqConfiguration {
        val config = DefaultConfiguration()
        config.setDataSource(datasource)
        config.setSQLDialect(SQLDialect.POSTGRES)
        return config
    }

}

@Factory
class DataConfiguration {

    @Bean
    @Singleton
    fun userDao(configuration: JooqConfiguration): UserDao = UserDao(configuration)

    @Bean
    @Singleton
    fun micropostDao(configuration: JooqConfiguration): MicropostDao = MicropostDao(configuration)

    @Bean
    @Singleton
    fun jooqDsl(configuration: JooqConfiguration): DSLContext = DSL.using(configuration)

    @Bean
    @Singleton
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator
}
