package com.alexgutjahr

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.junit.jupiter.api.Test

class SpringJwtAuthApplicationTests {

    @Test
    fun `generates secret keys`() {
      for (index in 1..10) {
        val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        println(Encoders.BASE64.encode(key.encoded))
      }
    }

}
