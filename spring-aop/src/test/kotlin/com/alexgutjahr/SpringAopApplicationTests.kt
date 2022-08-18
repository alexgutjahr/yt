package com.alexgutjahr

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.TimeUnit

@SpringBootTest
class SpringAopApplicationTests {

    @Autowired
    private lateinit var twitter: Twitter

    @Test
    fun `test Twitter API calls`() {
        println(twitter.user("heyalexgutjahr"))
        TimeUnit.SECONDS.sleep(5)
        println(twitter.user("heyalexgutjahr"))
    }

}
