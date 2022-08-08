package dev.ramadhani.jwttemplate.domain

import lombok.Data

@Data
class AccountCredential(
    val username: String,
    val password: String
)