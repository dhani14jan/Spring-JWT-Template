package dev.ramadhani.jwttemplate.domain

data class RegistrationForm(
    val username: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val firstPassword: String,
    val secondPassword: String,
)