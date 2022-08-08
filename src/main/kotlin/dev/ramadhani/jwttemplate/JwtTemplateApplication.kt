package dev.ramadhani.jwttemplate

import dev.ramadhani.jwttemplate.domain.User
import dev.ramadhani.jwttemplate.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
@SpringBootApplication
class JwtTemplateApplication(val userRepository: UserRepository, val encoder: PasswordEncoder): CommandLineRunner {
	companion object {
		val log: Logger = LoggerFactory.getLogger(JwtTemplateApplication::class.java)
	}
	override fun run(vararg args: String?) {
		val encodedPassword = encoder.encode("test123")
		val user = User("test", encodedPassword, "user")
		userRepository.save(user)
		val users = userRepository.findAll() as List<User>
		users.forEach {
			log.debug("Username: ${it.username}    Password: ${it.password}")
		}
	}

}

fun main(args: Array<String>) {
	runApplication<JwtTemplateApplication>(*args)
}
