package com.alexgutjahr

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Service
class NewsletterService {

    private val storage = ConcurrentHashMap<String, Subscriber>()

    @Cacheable(value = ["subscribers"])
    fun lookup(email: String): Subscriber {
        TimeUnit.SECONDS.sleep(5)
        return storage[email] ?: throw IllegalStateException("Subscriber $email not found!")
    }

//    We must return the value here, or it is not added to the cache
    @CachePut(value = ["subscribers"], key = "#subscriber.email")
    fun subscribe(subscriber: Subscriber): Subscriber {
        storage[subscriber.email] = subscriber
        return storage[subscriber.email]!!
    }

    @CacheEvict(value = ["subscribers"], key = "#subscriber.email")
    fun unsubscribe(subscriber: Subscriber) {
        storage.remove(subscriber.email)
    }

}

data class Subscriber(val email: String, val name: String)
