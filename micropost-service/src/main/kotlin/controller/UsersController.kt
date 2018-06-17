package micropost.controller

import data.dto.MicroPostDto
import data.dto.UserDto
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller("/users")
class UsersController {

    @Get("/")
    fun getAll(): List<UserDto> = listOf(UserDto(id = "test", email = "test@email.com"))

    @Get("/{id}")
    fun getOne(@Parameter("id") id: String): UserDto = UserDto(id = id, email = "$id@email.com")

    @Get("/{id}/posts")
    fun getPosts(id: String): List<MicroPostDto> = listOf(MicroPostDto(id = 1, content = "test"))

    @Post("/")
    fun save(@Body user: UserDto): HttpStatus = HttpStatus.CREATED

    @Put("/{id}")
    fun update(@Parameter("id") id: String, @Body user: UserDto) = Unit

    @Delete("/{id}")
    fun delete(@Parameter("id") id: String): HttpStatus = HttpStatus.NO_CONTENT

}