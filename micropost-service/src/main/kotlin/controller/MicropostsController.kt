package micropost.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.data.dto.MicropostDto
import micropost.data.mapper.toDto
import micropost.data.mapper.toEntity
import micropost.data.tables.Micropost
import micropost.data.tables.daos.MicropostDao
import org.jooq.DSLContext
import java.time.Instant

@Controller("/posts")
class MicropostsController(private val micropostDao: MicropostDao,
                           private val jooq: DSLContext) {

    @Get("/")
    fun getAll(): List<MicropostDto> = micropostDao.findAll()
            .map { it.toDto() }
            .toCollection(ArrayList())

    @Get("/{id}")
    fun getOne(id: Int): HttpResponse<MicropostDto> {
        val dto = micropostDao.findById(id)?.toDto() ?: return HttpResponse.notFound()
        return HttpResponse.ok(dto)
    }

    @Post("/")
    fun save(@Body post: MicropostDto): HttpStatus {
        post.createdAt = Instant.now().toString()
        micropostDao.insert(post.toEntity())
        return HttpStatus.CREATED
    }

    @Put("/{id}")
    fun update(id: Int, @Body post: MicropostDto): HttpStatus {
        val thatPost = micropostDao.findById(id) ?: return HttpStatus.NOT_FOUND
        jooq.newRecord(Micropost.MICROPOST, thatPost).let {
            it.content = post.content
            it.update()
        }

        return HttpStatus.OK
    }

    @Delete("/{id}")
    fun delete(id: Int): HttpStatus {
        micropostDao.deleteById(id)
        return HttpStatus.NO_CONTENT
    }

    @Get("/user/{id}")
    fun getPosts(id: String): List<MicropostDto> = micropostDao.fetchByUserId(id)
            .map { it.toDto() }
            .toCollection(ArrayList())

}