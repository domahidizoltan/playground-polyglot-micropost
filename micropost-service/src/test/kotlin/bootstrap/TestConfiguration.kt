package micropost.bootstrap

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import micropost.data.Tables
import micropost.data.tables.records.MicropostRecord
import micropost.data.tables.records.UserRecord
import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.MockConnection
import org.jooq.tools.jdbc.MockDataProvider
import org.jooq.tools.jdbc.MockExecuteContext
import org.jooq.tools.jdbc.MockResult
import java.sql.SQLException
import java.time.OffsetDateTime
import javax.inject.Singleton


@Factory
class TestDataConfiguration {

    @Bean
    @Singleton
    @Primary
    fun jooqDsl(): DSLContext {
        val provider = TestDataProvider()
        val connection = MockConnection(provider)
        return DSL.using(connection)
    }

}

class TestDataProvider : MockDataProvider {
    override fun execute(ctx: MockExecuteContext?): Array<MockResult> {
        val dsl = DSL.using(SQLDialect.POSTGRES)
        val sql = ctx!!.sql().toUpperCase()

        if (!sql.startsWith("SELECT")) {
            throw SQLException("Statement not supported: " + sql);
        } else {
            val result = when {
                sql.startsWith("SELECT \"PUBLIC\".\"USER\"") -> usersTestData(dsl)
                else -> micropostsTestData(dsl)
            }

            return arrayOf(MockResult(1, result))
        }

    }

    private fun usersTestData(dsl: DSLContext): Result<UserRecord> {
        val records = dsl.newRecord(Tables.USER)
                .values("johndoe", "johndoe@gmail.com", "John", "Doe")

        val usersResult = dsl.newResult(Tables.USER)
        usersResult.add(records)

        return usersResult
    }

    private fun micropostsTestData(dsl: DSLContext): Result<MicropostRecord> {
        val records = dsl.newRecord(Tables.MICROPOST)
                .values(1, "test content", OffsetDateTime.now(), "johndoe")

        val micropostsResult = dsl.newResult(Tables.MICROPOST)
        micropostsResult.add(records)

        return micropostsResult
    }

}

