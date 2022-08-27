package dev.ramadhani.jwttemplate.service

import dev.ramadhani.jwttemplate.domain.User
import dev.ramadhani.jwttemplate.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User? = username?.let {
            userRepository.findByUsername(username)
        }
        return user?.let {
            SpringUser
                .withUsername(it.username)
                .password(it.password)
                .roles(it.role)
                .accountLocked(!it.activated)
                .disabled(it.disabled)
                .build()
        } ?: throw UsernameNotFoundException("User not found!")
    }
}