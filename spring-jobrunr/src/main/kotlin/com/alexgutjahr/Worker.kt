package com.alexgutjahr

import org.jobrunr.jobs.annotations.Job
import org.jobrunr.jobs.context.JobContext
import org.jobrunr.spring.annotations.Recurring
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Worker {

    companion object {
        private val log = LoggerFactory.getLogger(Worker::class.java)
    }

    @Job(name = "Sync")
    @Recurring(id = "sync", cron = "*/30 * * * * *", zoneId = "Europe/Berlin")
    fun sync() {
         log.info("Syncing some data..")
    }

    @Job(name = "Backup")
    @Recurring(id = "backup", cron = "*/5 * * * * *", zoneId = "Europe/Berlin")
    fun backup(context: JobContext) {
        log.info("Backup started..")
        val bar = context.progressBar(100)
        for (i in 1..4) {
            TimeUnit.SECONDS.sleep(5)
            bar.setValue(i * 25)
        }
    }

}
