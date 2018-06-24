package micropost.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.data.dto.MicroPostDto
import micropost.data.dto.UserDto
import micropost.data.mapper.toDto
import micropost.data.tables.daos.UserDao

@Controller("/users")
class UsersController(private val userDao: UserDao) {

    @Get("/")
    fun getAll(): List<UserDto> = listOf(UserDto(id = "test", email = "test@email.com"))

    @Get("/{id}")
    fun getOne(id: String): UserDto = userDao.fetchOneById(id).toDto()

    @Get("/{id}/posts")
    fun getPosts(id: String): List<MicroPostDto> = listOf(MicroPostDto(id = 1, content = "test"))

    @Post("/")
    fun save(@Body user: UserDto): HttpStatus = HttpStatus.CREATED

    @Put("/{id}")
    fun update(id: String, @Body user: UserDto) = Unit

    @Delete("/{id}")
    fun delete(id: String): HttpStatus = HttpStatus.NO_CONTENT

}