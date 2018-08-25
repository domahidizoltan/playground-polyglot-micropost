package micropost.data.transport

import org.springframework.hateoas.ResourceSupport
import javax.validation.constraints.*

data class UserResource(var nickname: String? = null,
                        @get:Email var email: String? = null,
                        var firstName: String? = null,
                        var lastName: String? = null) : ResourceSupport()

data class MicropostResource(var postId:Int? = null,
                             @get:Size(min = 5, max = 140) var content: String? = null,
                             var createdAt: String? = null,
                             var userNickname: String? = null,
                             var statistics: PostStatisticsDto? = null) : ResourceSupport()

data class PostStatisticsDto(val postId: Int = 0,
                             val words: Int = 0,
                             val wordCounts: Map<String, Int>? = null)

data class UserResourceList(val resources: List<UserResource>,
                            val paging: PagingResource) : ResourceSupport()

data class MicropostResourceList(val resources: List<MicropostResource>,
                                 val paging: PagingResource) : ResourceSupport()
