package micropost.data.mapper

import micropost.data.transport.MicropostResource
import micropost.data.transport.UserResource
import micropost.data.tables.pojos.Micropost
import micropost.data.tables.pojos.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.time.*

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


@Mapper(uses = [DateMapper::class])
interface MicropostMapper {
    @Mapping(target = "statistics", ignore = true)
    fun toMicropostResource(micropost: Micropost): MicropostResource
}

val micropostMapper: MicropostMapper = Mappers.getMapper(MicropostMapper::class.java)

fun Micropost.toResource(): MicropostResource {
    return micropostMapper.toMicropostResource(this)
}

fun MicropostResource.toEntity(): Micropost {
    val zoneInstant = Instant.parse(createdAt)?.atZone(ZoneId.systemDefault())
    return Micropost(null, content, OffsetDateTime.from(zoneInstant), userNickname)
}


class DateMapper {
    fun map(value: OffsetDateTime): String = value.toInstant().toString()
}