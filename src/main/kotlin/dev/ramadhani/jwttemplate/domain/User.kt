package dev.ramadhani.jwttemplate.domain

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name="users")
class User(
    @Column(nullable = false, unique = true)
    val username: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false, unique = true)
    val email: String,
    @Column(nullable = false)
    val firstname: String,
    @Column(nullable = false)
    val lastname: String,
    @Column(nullable = false)
    val role: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long? = null
)