package com.twitter.plugins

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*

fun Application.configureSecurity() {
    
    authentication {
            jwt {
                val jwtAudience = environment.config.property("jwt.audience").getString()
                realm = environment.config.property("jwt.realm").getString()
                verifier(
                    JWT
                        .require(Algorithm.HMAC256("secret"))
                        .withAudience(jwtAudience)
                        .withIssuer(environment.config.property("jwt.domain").getString())
                        .build()
                )
                validate { credential ->
                    if (credential.payload.audience.contains(jwtAudience)) {
                        JWTPrincipal(credential.payload)
                    } else null
                }
            }
        }

}

val JWTPrincipal.userId: String?
    get() = getClaim("userId", String::class)