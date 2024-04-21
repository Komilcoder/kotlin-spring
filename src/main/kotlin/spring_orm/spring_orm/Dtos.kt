package spring_orm.spring_orm

data class UserCreateDto(
    val username: String,
    val password: String,
    val role: Role,
    val fullname: String
){
    fun toEntity(): User{
        return User(fullname, username, password, role)
    }
}

data class UserUpdate(
    val username: String?,
    val password: String?,
    val role: Role?,
    val fullname: String?
)

data class GetUserDto(
    val id: Long,
    val username: String,
    val role: Role,
    val fullname: String
){
    companion object{
//        fun toDto(user: User): GetUserDto{
//            return user.run {
//                GetUserDto(id!!, username, role, fullname,)
//            }
//
//        }
        fun toDto(user: User)= user.run { GetUserDto(id!!, username, role, fullname,) }


    }
}

//profile

data class ProfileCreateDto(
    val user: String,

)

data class ProfileUpdate(
    val username: String?,
    val password: String?,
    val role: Role?,
    val fullname: String?
)

data class GetProfileDto(
    val id: Long,
    val username: String,
    val role: Role,
    val fullname: String
)