import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import micropost.controller.POSTS_PATH
import micropost.controller.USERS_PATH
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.springframework.hateoas.Link

object HalSpec: Spek({

    val server = ApplicationContext.run(EmbeddedServer::class.java)
    val client = HttpClient.create(server.url)
    val firstPage = "?size=1&page=0"

    given("User listing endpoint") {

        on("Get paged users") {
            val pagedUsersRequest: HttpRequest<String> = HttpRequest.GET("$USERS_PATH$firstPage")
            val userResourceListJson = client.toBlocking()
                    .retrieve<String>(pagedUsersRequest)
            val userListResource = Configuration.defaultConfiguration()
                    .jsonProvider()
                    .parse(userResourceListJson)

            it("Contains paging and HAL links") {
                val expectedNickname = "johndoe"
                with(userListResource.jsonAsserts()) {
                    "$.resources[0].nickname" has expectedNickname
                    "$.resources[0].email" has "$expectedNickname@gmail.com"
                    "$.resources[0].links[0]" hasLink  Link("$USERS_PATH/$expectedNickname", "read")
                    "$.resources[0].links[1]" hasLink Link("/posts/user/$expectedNickname", "post")

                    "$.paging" hasPaging Paging()
                    "$.links[0]" hasLink Link(USERS_PATH, "create")
                }
            }
        }
    }

    given("Micropost listing endpoint") {

        on("Get paged posts") {
            val pagedPostsRequest: HttpRequest<String> = HttpRequest.GET("$POSTS_PATH$firstPage")
            val postListResourceJson = client.toBlocking()
                    .retrieve<String>(pagedPostsRequest)
            val postListResource = Configuration.defaultConfiguration()
                    .jsonProvider()
                    .parse(postListResourceJson)

            it("Contains paging and HAL links") {
                val expectedNickname = "johndoe"
                val expectedPostId = 1
                with(postListResource.jsonAsserts()) {
                    "$.resources[0].postId" has expectedPostId
                    "$.resources[0].content" has "test content"
                    "$.resources[0].userNickname" has expectedNickname
                    "$.resources[0].links[0]" hasLink  Link("$POSTS_PATH/$expectedPostId", "read")
                    "$.resources[0].links[1]" hasLink Link("$USERS_PATH/$expectedNickname", "user")

                    "$.paging" hasPaging Paging()
                    "$.links[0]" hasLink Link(POSTS_PATH, "create")
                }
            }
        }
    }

    afterGroup {
        client.close()
        server.close()
    }

})


data class Paging(val page: Int=0, val size: Int=1, val totalItems: Int=1, val totalPages: Int=1)

class JsonPathAssert(private val underTest: Any) {

    infix fun String.has(expectedValue: Any) {
        assertThat(underTest, hasJsonPath(this, equalTo(expectedValue)))
    }

    infix fun String.hasLink(expectedLink: Link) {
        assertThat(underTest, hasJsonPath(this + ".href", equalTo(expectedLink.href)))
        assertThat(underTest, hasJsonPath(this + ".rel", equalTo(expectedLink.rel)))
    }

    infix fun String.hasPaging(paging: Paging) {
        assertThat(underTest, hasJsonPath(this + ".page", equalTo(paging.page)))
        assertThat(underTest, hasJsonPath(this + ".size", equalTo(paging.size)))
        assertThat(underTest, hasJsonPath(this + ".totalItems", equalTo(paging.totalItems)))
        assertThat(underTest, hasJsonPath(this + ".totalPages", equalTo(paging.totalPages)))
    }

}

private fun Any.jsonAsserts(): JsonPathAssert {
    return JsonPathAssert(this)
}