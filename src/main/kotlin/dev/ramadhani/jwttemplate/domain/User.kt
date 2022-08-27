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
    var password: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var firstname: String,
    @Column(nullable = false)
    var lastname: String,
    @Column(nullable = false)
    val role: String,
    @Column(nullable=false)
    var activated: Boolean = false,
    @Column(nullable=false)
    var disabled: Boolean = false,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long? = null
)