package dev.ramadhani.jwttemplate.service

import dev.ramadhani.jwttemplate.domain.RegistrationForm
import dev.ramadhani.jwttemplate.domain.User
import dev.ramadhani.jwttemplate.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class RegistrationService(val userRepository: UserRepository, val encoder: PasswordEncoder) {

    fun isEmailUsed(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    fun isUsernameUsed(username: String): Boolean {
        return userRepository.findByUsername(username) != null
    }

    fun registerUser(form: RegistrationForm): User {
        return userRepository.save(User(form.username, encoder.encode(form.firstPassword), form.email, form.firstname, form.lastname, "USER"))
    }
}