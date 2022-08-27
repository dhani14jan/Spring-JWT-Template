package dev.ramadhani.jwttemplate.repository

import dev.ramadhani.jwttemplate.domain.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.activated = ?1 WHERE u.username = ?2")
    fun updateActivateByUsername(activate: Boolean, username: String)

}