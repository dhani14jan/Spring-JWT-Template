package dev.ramadhani.jwttemplate.publisher

import dev.ramadhani.jwttemplate.domain.RegistrationEvent
import dev.ramadhani.jwttemplate.domain.User
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
class RegistrationEventPublisher(val publisher: ApplicationEventPublisher) {
    fun publishEvent(user: User) {
        publisher.publishEvent(RegistrationEvent(user))
    }
}