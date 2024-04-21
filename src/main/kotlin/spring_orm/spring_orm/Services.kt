package spring_orm.spring_orm

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

interface UserService {
    fun createUser(dto: UserCreateDto): GetUserDto
    fun userUpdate(id:Long, dto: UserUpdate): GetUserDto
    fun deleteUser(id: Long): GetUserDto

    fun getAllUser(pageable: Pageable): Page<GetUserDto>
}

interface ProfileService {
    fun createUser(dto: ProfileCreateDto): GetProfileDto
    fun userUpdate(id:Int, dto: ProfileUpdate): GetProfileDto
    fun deleteUser(id: Int): GetProfileDto

    fun getAllUser(pageable: Pageable): Page<GetProfileDto>
}

@Service
class UserServiceImpl(private val repository: UserRepository): UserService{
    override fun createUser(dto: UserCreateDto): GetUserDto {
        if (repository.existsByUsername(dto.username))
            throw UserAlreadyExistsException(dto.username)
        val user = repository.save(dto.toEntity())
        return GetUserDto.toDto(user)
    }

    override fun userUpdate(id: Long, dto: UserUpdate): GetUserDto {
        val user = repository.findByIdOrNull(id) ?: throw UserNotFoundException()

        dto.username?.let {
            if (user.username != it && repository.existsByUsername(dto.username))
                throw UserAlreadyExistsException(dto.username)
            user.username = it
        }
        dto.password?.let {}
        dto.role?.let {}
        dto.fullname?.let {}
        repository.save(user)
        return GetUserDto.toDto(user)
    }

    override fun deleteUser(id: Long){
        repository.deleteById(id)
    }

    override fun getUserByid(id: Long): GetUserDto{
        val user = repository.findByIdOrNull(id) ?: throw UserNotFoundException()
        return GetUserDto.toDto(user)
    }

    override fun getAllUser(pageable: Pageable): Page<GetUserDto> {
        val response = repository.findAll(pageable)
//        return response.map { GetUserDto.toDto(it) }
        return repository.findAll(pageable).map ( GetUserDto.Companion::toDto )
    }
}