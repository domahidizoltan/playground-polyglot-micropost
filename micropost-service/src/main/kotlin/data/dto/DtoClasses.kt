package data.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class UserDto(val id: String = "",
                   val email: String = "",
                   val firstName: String = "",
                   val lastName: String = "",
                   val posts: List<MicroPostDto>? = null)

data class MicroPostDto(val id: Int = 0,
                        val content: String = "",
                        val createdAt: Instant = Instant.now(),
                        val user: UserDto? = null,
                        val statistics: PostStatisticsDto? = null)

data class PostStatisticsDto(val postId: Int = 0,
                             val words: Int = 0,
                             val wordCounts: Map<String, Int>? = null)
