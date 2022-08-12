package com.alexgutjahr

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager

@SpringBootTest
class SpringCachingApplicationTests {

    @Autowired
    private lateinit var service: NewsletterService

    @Autowired
    private lateinit var manager: CacheManager

    @Test
    fun `cache works`() {
        val cache = manager.getCache("subscribers")!!
        val subscriber = Subscriber("alex@alexgutjahr.com", "Alex")

        Assertions.assertNull(cache.get(subscriber.email))

        service.subscribe(subscriber)
        Assertions.assertNotNull(cache.get(subscriber.email))
        Assertions.assertEquals(subscriber, service.lookup(subscriber.email))

        service.unsubscribe(subscriber)
        Assertions.assertNull(cache.get(subscriber.email))
        Assertions.assertThrows(IllegalStateException::class.java) {
            service.lookup(subscriber.email)
        }
    }

}
