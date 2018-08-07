package micropost.data.mapper

import micropost.data.dto.MicropostDto
import micropost.data.dto.UserDto
import micropost.data.tables.pojos.Micropost
import micropost.data.tables.pojos.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.time.*

@Mapper
interface UserMapper {
    fun toUserDto(user: User): UserDto
}

val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

fun User.toDto(): UserDto {
    return userMapper.toUserDto(this)
}

fun UserDto.toEntity(): User {
    return User(id, email, firstName, lastName)
}


@Mapper(uses = [DateMapper::class])
interface MicropostMapper {
    @Mapping(target = "statistics", ignore = true)
    fun toMicropostDto(micropost: Micropost): MicropostDto
}

val micropostMapper: MicropostMapper = Mappers.getMapper(MicropostMapper::class.java)

fun Micropost.toDto(): MicropostDto {
    return micropostMapper.toMicropostDto(this)
}

fun MicropostDto.toEntity(): Micropost {
    val zoneInstant = Instant.parse(createdAt)?.atZone(ZoneId.systemDefault())
    return Micropost(null, content, OffsetDateTime.from(zoneInstant), userId)
}


class DateMapper {
    fun map(value: OffsetDateTime): String = value.toInstant().toString()
}