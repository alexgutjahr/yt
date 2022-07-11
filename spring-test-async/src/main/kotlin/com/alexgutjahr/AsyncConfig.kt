package com.alexgutjahr

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@Configuration
@Profile("!test")
class AsyncConfig
