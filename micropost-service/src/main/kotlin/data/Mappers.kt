package micropost.data.mapper

import micropost.data.dto.UserDto
import org.mapstruct.Mapper
import micropost.data.tables.pojos.User
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper {
    fun toUserDto(user: User):UserDto
}


val userMapper = Mappers.getMapper(UserMapper::class.java)

fun User.toDto(): UserDto {
    return userMapper.toUserDto(this)
}
