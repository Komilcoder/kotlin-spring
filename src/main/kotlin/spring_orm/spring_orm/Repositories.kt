package spring_orm.spring_orm

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>{
    fun findUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}
