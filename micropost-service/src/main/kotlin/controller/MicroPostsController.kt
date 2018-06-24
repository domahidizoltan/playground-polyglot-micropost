package micropost.controller

import micropost.data.dto.MicroPostDto
import micropost.data.dto.UserDto
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Body
import java.time.Instant

@Controller("/posts")
class MicroPostsController {

    @Get("/")
    fun getAll(): List<MicroPostDto> = listOf(makeDummyPost(1))

    @Get("/{id}")
    fun getOne(id: Int): MicroPostDto = makeDummyPost(id)

    @Post("/")
    fun save(@Body post: MicroPostDto): HttpStatus = HttpStatus.CREATED

    @Put("/{id}")
    fun update(id: Int, @Body post: MicroPostDto) = Unit

    @Delete("/{id}")
    fun delete(id: Int): HttpStatus = HttpStatus.NO_CONTENT

    private fun makeDummyPost(id: Int): MicroPostDto {
        val user = UserDto(id = "id", email = "email")
        val post = MicroPostDto(id = id, content = "test", createdAt = Instant.now(), user = user)
        return post
    }

}