package dev.ramadhani.jwttemplate.controller

import dev.ramadhani.jwttemplate.domain.AccountCredential
import dev.ramadhani.jwttemplate.domain.RegistrationForm
import dev.ramadhani.jwttemplate.publisher.RegistrationEventPublisher
import dev.ramadhani.jwttemplate.service.JwtService
import dev.ramadhani.jwttemplate.service.RegistrationService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1"])
class AuthController(val jwtService: JwtService, val authenticationManager: AuthenticationManager, val registrationService: RegistrationService, val registrationEventPublisher: RegistrationEventPublisher) {
    @PostMapping("/login")
    fun login(@RequestBody credential: AccountCredential): ResponseEntity<Any> {
        val creds = UsernamePasswordAuthenticationToken(credential.username, credential.password)
        val auth: Authentication = authenticationManager.authenticate(creds)


        val jwt: String = jwtService.getToken(auth.name)
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer $jwt")
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
            .body(mapOf("message" to "Hello ${auth.name}"))
    }

    @GetMapping("/logged-in")
    fun isLoggedIn(): ResponseEntity<Any> {
        val result = mapOf<String, String>("message" to "You are logged in!")
        return ResponseEntity.ok()
            .body(result)
    }

    @PostMapping("/register")
    fun register(@RequestBody form: RegistrationForm): ResponseEntity<Any> {
        val result = mutableMapOf<String, String>()
        var hasError = false
        if(registrationService.isUsernameUsed(form.username)) {
            result["username"] = "Username is already used!"
            hasError = true
        }
        if(registrationService.isEmailUsed(form.email)) {
            result["email"] = "Email is already used!"
            hasError = true
        }
        if(hasError) {
            return ResponseEntity.badRequest()
                .body(result)
        }
        val user = registrationService.registerUser(form);
        registrationEventPublisher.publishEvent(user)

        return ResponseEntity.ok()
            .body(mapOf("message" to "User is created!"))
    }


}