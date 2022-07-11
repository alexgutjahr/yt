package com.alexgutjahr

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Service
class MyService {

    private val stash = ConcurrentHashMap<String, Int>()

    @Async
    fun calculate() {
        TimeUnit.SECONDS.sleep(5)
        stash["answer"] = 42
    }

    fun getAnswer(): Int {
        return stash["answer"] ?: 0
    }

}
