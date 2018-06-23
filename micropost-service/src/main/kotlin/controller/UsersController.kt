package micropost.controller

import data.dto.MicroPostDto
import data.dto.UserDto
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.data.tables.daos.UserDao

@Controller("/users")
class UsersController(private val userDao: UserDao) {

    @Get("/")
    fun getAll(): List<UserDto> = listOf(UserDto(id = "test", email = "test@email.com"))

    @Get("/{id}")
    fun getOne(id: String): UserDto {
        val user = userDao.fetchOneById(id)
        return UserDto(id = user.id, email = user.email)
    }

    @Get("/{id}/posts")
    fun getPosts(id: String): List<MicroPostDto> = listOf(MicroPostDto(id = 1, content = "test"))

    @Post("/")
    fun save(@Body user: UserDto): HttpStatus = HttpStatus.CREATED

    @Put("/{id}")
    fun update(id: String, @Body user: UserDto) = Unit

    @Delete("/{id}")
    fun delete(id: String): HttpStatus = HttpStatus.NO_CONTENT

}