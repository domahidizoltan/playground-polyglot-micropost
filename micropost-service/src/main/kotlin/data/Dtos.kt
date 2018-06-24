package micropost.data.dto

import java.time.Instant

data class UserDto(var id: String? = null,
                   var email: String? = null,
                   var firstName: String? = null,
                   var lastName: String? = null,
                   var posts: List<MicroPostDto>? = null)

data class MicroPostDto(val id: Int = 0,
                        val content: String = "",
                        val createdAt: Instant = Instant.now(),
                        val user: UserDto? = null,
                        val statistics: PostStatisticsDto? = null)

data class PostStatisticsDto(val postId: Int = 0,
                             val words: Int = 0,
                             val wordCounts: Map<String, Int>? = null)
