package com.alexgutjahr

import org.jobrunr.scheduling.JobScheduler
import org.jobrunr.scheduling.cron.Cron
import org.springframework.stereotype.Component
import java.time.Instant
import javax.annotation.PostConstruct

@Component
class JobFactory(private val scheduler: JobScheduler) {

    @PostConstruct
    fun schedule() {

//        Fire & Forget
        scheduler.enqueue {
            println("Fire & Forget!!")
        }

//        Scheduled
        scheduler.schedule(Instant.now().plusSeconds(60)) {
            println("I am a bit behind!!")
        }

//        Recurring
        scheduler.scheduleRecurrently(Cron.daily()) {
            println("I am a daily recurring job!!")
        }
    }

}
