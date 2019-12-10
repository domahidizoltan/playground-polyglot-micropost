package micropost.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import micropost.core.HalBuilder
import micropost.core.paginate
import micropost.core.validateRequest
import micropost.data.mapper.toEntity
import micropost.data.mapper.toResource
import micropost.data.tables.User.*
import micropost.data.tables.daos.UserDao
import micropost.data.transport.*
import org.jooq.DSLContext
import javax.annotation.Nullable
import javax.validation.Validator

const val USERS_PATH = "/users"

@Controller(USERS_PATH)
class UsersController(private val userDao: UserDao,
                      private val jooq: DSLContext,
                      private val validator: Validator) {

    private val hal = HalBuilder.forPath(USERS_PATH)
    private val readLink = { nickname:String -> hal.buildLink("read", "/{nickname}", nickname) }
    private val createLink = { hal.buildLink("create", "") }
    private val updateLink = { nickname:String -> hal.buildLink("update", "/{nickname}", nickname) }
    private val deleteLink = { nickname:String -> hal.buildLink("delete", "/{nickname}", nickname) }

    private val postHal = HalBuilder.forPath(POSTS_PATH)
    private val postLink = { postId:String -> postHal.buildLink("post", "/user/{nickname}", postId) }

    @Get("/")
    fun getAll(@Nullable @QueryValue("page") page: Int?,
               @Nullable @QueryValue("size") size: Int?): HttpResponse<UserResourceList> {
        val resources = jooq.selectFrom(USER)
                .paginate(page, size)
                .orderBy(USER.NICKNAME.asc())
                .fetchInto(UserResource::class.java)

        resources.forEach {
            with(it) {
                it.add(readLink(it.nickname!!))
                it.add(postLink(it.nickname!!))
            }
        }

        val total = jooq.fetchCount(USER)
        val paging = PagingResource(page, size, total, "$USERS_PATH/")
        val resourceList = UserResourceList(resources, paging)
        resourceList.add(createLink())

        return HttpResponse.ok(resourceList)
    }

    @Get("/{nickname}")
    fun getOne(nickname: String): HttpResponse<UserResource> {
        val resource = userDao.fetchOneByNickname(nickname)?.toResource() ?: return HttpResponse.notFound()

        with(resource) {
            add(updateLink(nickname))
            add(deleteLink(nickname))
            add(postLink(nickname))
        }
        return HttpResponse.ok(resource)
    }

    @Post("/")
    fun save(@Body user: UserResource): HttpResponse<UserResource> {
        validator.validateRequest(user)
        userDao.insert(user.toEntity())

        val resource = UserResource()
        with(resource) {
            add(readLink(user.nickname!!))
            add(updateLink(user.nickname!!))
            add(deleteLink(user.nickname!!))
        }
        return HttpResponse.created(resource)
    }

    @Put("/{nickname}")
    fun update(nickname: String, @Body user: UserResource): HttpResponse<UserResource> {
        validator.validateRequest(user)
        val thatUser = userDao.findById(nickname) ?: return HttpResponse.notFound()
        jooq.newRecord(USER, thatUser).let {
            it.email = user.email
            it.firstName = user.firstName
            it.lastName = user.lastName
            it.update()
        }

        val resource = UserResource()
        resource.add(deleteLink(nickname))
        return HttpResponse.ok(resource)
    }

    @Delete("/{nickname}")
    fun delete(nickname: String): HttpStatus {
        userDao.deleteById(nickname)
        return HttpStatus.NO_CONTENT
    }

}
