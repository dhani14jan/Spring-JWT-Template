package dev.ramadhani.jwttemplate.listener

import dev.ramadhani.jwttemplate.domain.RegistrationEvent
import dev.ramadhani.jwttemplate.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RegistrationEventListener(val userRepository: UserRepository) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener
    fun handleRegistrationEvent(event: RegistrationEvent) {
        //Do registration verification via email or phone number here


        //No email or phone number verification
        userRepository.updateActivateByUsername( true, event.user.username)
        log.debug("Account: ${event.user.username} is activated!")

    }

}