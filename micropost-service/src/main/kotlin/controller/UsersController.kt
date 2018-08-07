package micropost.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.data.dto.UserDto
import micropost.data.mapper.toDto
import micropost.data.mapper.toEntity
import micropost.data.tables.User
import micropost.data.tables.daos.UserDao
import org.jooq.DSLContext

@Controller("/users")
class UsersController(private val userDao: UserDao,
                      private val jooq: DSLContext) {

    @Get("/")
    fun getAll(): List<UserDto> = userDao.findAll()
            .map { it.toDto() }
            .toCollection(ArrayList())

    @Get("/{id}")
    fun getOne(id: String): HttpResponse<UserDto> {
        val dto = userDao.fetchOneById(id)?.toDto() ?: return HttpResponse.notFound()
        return HttpResponse.ok(dto)
    }

    @Post("/")
    fun save(@Body user: UserDto): HttpStatus {
        userDao.insert(user.toEntity())
        return HttpStatus.CREATED
    }

    @Put("/{id}")
    fun update(id: String, @Body user: UserDto): HttpStatus {
        val thatUser = userDao.findById(id) ?: return HttpStatus.NOT_FOUND
        jooq.newRecord(User.USER, thatUser).let {
            it.email = user.email
            it.firstName = user.firstName
            it.lastName = user.lastName
            it.update()
        }

        return HttpStatus.OK
    }

    @Delete("/{id}")
    fun delete(id: String): HttpStatus {
        userDao.deleteById(id)
        return HttpStatus.NO_CONTENT
    }

}