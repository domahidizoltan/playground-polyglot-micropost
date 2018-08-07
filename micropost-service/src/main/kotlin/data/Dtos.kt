package micropost.data.dto

data class UserDto(var id: String? = null,
                   var email: String? = null,
                   var firstName: String? = null,
                   var lastName: String? = null)

data class MicropostDto(var id:Int? = null,
                        var content: String? = null,
                        var createdAt: String? = null,
                        var userId: String? = null,
                        var statistics: PostStatisticsDto? = null)

data class PostStatisticsDto(val postId: Int = 0,
                             val words: Int = 0,
                             val wordCounts: Map<String, Int>? = null)
