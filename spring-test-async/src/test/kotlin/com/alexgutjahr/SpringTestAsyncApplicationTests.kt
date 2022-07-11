package com.alexgutjahr

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringTestAsyncApplicationTests {

    @Autowired
    private lateinit var service: MyService

    @Test
    fun `service calculates correct answer`() {
        service.calculate()
        Assertions.assertEquals(42, service.getAnswer())
    }

}
