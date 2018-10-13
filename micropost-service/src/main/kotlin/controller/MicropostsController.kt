package micropost.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.core.HalBuilder
import micropost.core.paginate
import micropost.core.validateRequest
import micropost.data.transport.MicropostResource
import micropost.data.transport.MicropostResourceList
import micropost.data.mapper.toResource
import micropost.data.mapper.toEntity
import micropost.data.tables.Micropost.*
import micropost.data.tables.daos.MicropostDao
import micropost.data.transport.PagingResource
import micropost.service.PostStatisticsClient
import org.jooq.DSLContext
import java.time.Instant
import javax.annotation.Nullable
import javax.validation.Validator

const val POSTS_PATH = "/posts"

@Controller(POSTS_PATH)
class MicropostsController(private val micropostDao: MicropostDao,
                           private val jooq: DSLContext,
                           private val validator: Validator,
                           private val postStatisticsClient: PostStatisticsClient) {

    private val hal = HalBuilder.forPath(POSTS_PATH)
    private val readLink = { postId:Int -> hal.buildLink("read", "/{postId}", postId) }
    private val createLink = { hal.buildLink("create", "") }
    private val updateLink = { postId:Int -> hal.buildLink("update", "/{postId}", postId) }
    private val deleteLink = { postId:Int -> hal.buildLink("delete", "/{postId}", postId) }

    private val userHal = HalBuilder.forPath(USERS_PATH)
    private val userLink = { nickname:String -> userHal.buildLink("user", "/{nickname}", nickname) }

    @Get("/")
    fun getAll(@Nullable @QueryValue("page") page: Int?,
               @Nullable @QueryValue("size") size: Int?): HttpResponse<MicropostResourceList> {
        val resources = jooq.selectFrom(MICROPOST)
                .paginate(page, size)
                .fetchInto(MicropostResource::class.java)

        resources.forEach {
            with(resources) {
                it.add(readLink(it.postId!!))
                it.add(userLink(it.userNickname!!))
            }
        }

        val total = jooq.fetchCount(MICROPOST)
        val paging = PagingResource(page, size, total, "$POSTS_PATH/")
        val resourceList = MicropostResourceList(resources, paging)
        resourceList.add(createLink())

        return HttpResponse.ok(resourceList)
    }

    @Get("/{postId}")
    fun getOne(postId: Int): HttpResponse<MicropostResource> {
        val resource = micropostDao.findById(postId)?.toResource() ?: return HttpResponse.notFound()

        with(resource) {
            add(updateLink(postId))
            add(deleteLink(postId))
            add(userLink(resource.userNickname!!))
        }
        return HttpResponse.ok(resource)
    }

    @Post("/")
    fun save(@Body post: MicropostResource): HttpResponse<MicropostResource> {
        validator.validateRequest(post)
        post.createdAt = Instant.now().toString()
        val entity = post.toEntity()
        val postId = jooq.insertInto(MICROPOST)
                .columns(MICROPOST.CONTENT, MICROPOST.CREATED_AT, MICROPOST.USER_NICKNAME)
                .values(entity.content, entity.createdAt, entity.userNickname)
                .returning(MICROPOST.POST_ID)
                .fetchOne()
                .postId

        postStatisticsClient.sendPostStatistics(postId, post.content!!)

        val resource = MicropostResource()
        with(resource) {
            add(readLink(postId))
            add(updateLink(postId))
            add(deleteLink(postId))
        }

        return HttpResponse.created(resource)
    }

    @Put("/{postId}")
    fun update(postId: Int, @Body post: MicropostResource): HttpResponse<MicropostResource> {
        validator.validateRequest(post)
        val thatPost = micropostDao.findById(postId) ?: return HttpResponse.notFound()
        jooq.newRecord(MICROPOST, thatPost).let {
            it.content = post.content
            it.update()
        }

        postStatisticsClient.sendPostStatistics(postId, post.content!!)

        val resource = MicropostResource()
        resource.add(deleteLink(postId))
        return HttpResponse.ok(resource)
    }

    @Delete("/{postId}")
    fun delete(postId: Int): HttpStatus {
        micropostDao.deleteById(postId)
        return HttpStatus.NO_CONTENT
    }

    @Get("/user/{nickname}")
    fun getPosts(nickname: String,
                 @Nullable @QueryValue("page") page: Int?,
                 @Nullable @QueryValue("size") size: Int?): HttpResponse<MicropostResourceList> {
        val query = jooq.selectFrom(MICROPOST)
                .where(MICROPOST.USER_NICKNAME.eq(nickname))
        val resources = query.paginate(page, size)
                .fetchInto(MicropostResource::class.java)

        resources.forEach{ it.add(readLink(it.postId!!)) }

        val total = jooq.fetchCount(query)
        val paging = PagingResource(page, size, total, "/users/$nickname")
        val resourceList = MicropostResourceList(resources, paging)
        with(resourceList) {
            add(userLink(nickname))
        }


        return HttpResponse.ok(resourceList)
    }

}