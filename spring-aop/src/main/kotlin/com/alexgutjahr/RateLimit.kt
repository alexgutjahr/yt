package com.alexgutjahr

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.Duration

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RateLimit

@Aspect
@Component
class TwitterRateLimiter {

    companion object {
        private val log = LoggerFactory.getLogger(TwitterRateLimiter::class.java)
    }

//    Allow 3 requests per 15 minutes, but no more than 1 every 5 seconds
    private val bucket = Bucket.builder()
        .addLimit(Bandwidth.simple(3, Duration.ofMinutes(15)))
        .addLimit(Bandwidth.simple(1, Duration.ofSeconds(5)))
        .build()

    @Around("@annotation(RateLimit)")
    fun limit(point: ProceedingJoinPoint): Any {
        log.info("Checking rate limit..")
        if (bucket.tryConsume(1)) {
            return point.proceed()
        }

        throw IllegalStateException("API call exceeds rate limit.")
    }

}
