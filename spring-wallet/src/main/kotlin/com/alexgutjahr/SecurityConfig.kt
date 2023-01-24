package com.alexgutjahr

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SecurityConfig {

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun auth(users: UserService): AuthenticationManager = ProviderManager(Web3AuthenticationProvider(users))

    @Bean
    fun cors() = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry
                .addMapping("/**")
                .allowedOriginPatterns("http://localhost:5173")
                .allowedMethods("*")
        }
    }

    @Bean
    fun chain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests()
            .mvcMatchers(HttpMethod.GET,  "/challenge/{address}").permitAll()
            .mvcMatchers(HttpMethod.POST, "/auth").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .csrf().disable()
            .logout().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors()

        return http.build()
    }

}