package dev.ramadhani.jwttemplate.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtService {
    companion object {
        @JvmStatic
        val EXPIRATION_TIME = 86400000
        @JvmStatic
        val PREFIX = "Bearer"
        @JvmStatic
        val KEY: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    fun getToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(KEY)
            .compact()
    }

    fun getAuthUser(token: String?): String? {
        return token?.let {
            Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(it.replace(PREFIX, ""))
                .body
                .subject
        }
    }
}