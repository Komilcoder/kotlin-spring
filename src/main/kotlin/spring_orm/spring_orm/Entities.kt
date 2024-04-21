package spring_orm.spring_orm

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.cglib.core.GeneratorStrategy

@Entity
@Table(name = "Users")
class User(
    @Column(nullable = false) val fullname: String,
    @Column(unique = true, nullable = false, length = 32) var username: String,
    @Column(nullable = false, length = 100) val password: String,
    val role: Role,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null

)

@Entity
class Profile(
    @OneToOne val user: User,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?=null

)

