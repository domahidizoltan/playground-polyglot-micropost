package micropost.data.mapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import micropost.data.Tables.MICROPOST
import micropost.data.Tables.POST_STATISTICS
import micropost.data.tables.pojos.Micropost
import micropost.data.tables.pojos.User
import micropost.data.transport.MicropostResource
import micropost.data.transport.PostStatisticsDto
import micropost.data.transport.UserResource
import org.jooq.Record
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*


@Mapper
interface UserMapper {
    fun toUserResource(user: User): UserResource
}

val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

fun User.toResource(): UserResource {
    return userMapper.toUserResource(this)
}

fun UserResource.toEntity(): User {
    return User(nickname, email, firstName, lastName)
}


fun MicropostResource.toEntity(): Micropost {
    val zoneInstant = Instant.parse(createdAt)?.atZone(ZoneId.systemDefault())
    return Micropost(null, userNickname, content, OffsetDateTime.from(zoneInstant))
}

fun Record.toResource(objectMapper: ObjectMapper): MicropostResource {
    val typeRef = object : TypeReference<HashMap<String, Int>>() {}
    val statistics = PostStatisticsDto(
            postId = this.get(POST_STATISTICS.POST_ID),
            totalWords = this.get(POST_STATISTICS.TOTAL_WORDS),
            alphanumeric = this.get(POST_STATISTICS.ALPHANUMERIC),
            nonAlphanumeric = this.get(POST_STATISTICS.NON_ALPHANUMERIC),
            wordOccurrence = objectMapper.readValue(this.get(POST_STATISTICS.WORD_OCCURRENCE), typeRef)
    )
    return MicropostResource(
            postId = this.get(MICROPOST.POST_ID),
            content = this.get(MICROPOST.CONTENT),
            createdAt = DateMapper().map(this.get(MICROPOST.CREATED_AT)),
            statistics = statistics,
            userNickname = this.get(MICROPOST.USER_NICKNAME)
    )
}


class DateMapper {
    fun map(value: OffsetDateTime): String = value.toInstant().toString()
}