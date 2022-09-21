package com.alexgutjahr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebSecurity
@SpringBootApplication
class SpringSecurityCorsApplication {

    @Bean
    fun cors(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("http://example.com").allowedMethods("*")
            }
        }
    }

    @Bean
    fun chain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests().anyRequest().authenticated()
        http.cors()

        return http.build()
    }

}

fun main(args: Array<String>) {
    runApplication<SpringSecurityCorsApplication>(*args)
}
