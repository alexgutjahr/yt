package com.alexgutjahr

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size
import kotlin.reflect.KClass

@RestController
class RegistrationController {

    companion object {
        private val log = LoggerFactory.getLogger(RegistrationController::class.java)
    }

    @PostMapping("/registrations")
    fun register(@Valid @RequestBody request: RegistrationRequest) {
        log.info("Registered $request")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun onError(cause: BindException): ApiError {
        val fields = cause.fieldErrors.associate { it.field to it.defaultMessage }
        val global = cause.globalErrors.map { it.defaultMessage }

        return ApiError(fields, global)
    }

}

data class ApiError(val fields: Map<String, String?>, val global: List<String?>)

@PasswordMatch
data class RegistrationRequest(
    val name: String?,
    @field:Email
    val username: String,
    @field:Size(min = 8)
    val password: String,
    val confirmation: String,
    @field:Age(min = 18)
    val birthdate: LocalDate)

@MustBeDocumented
@Constraint(validatedBy = [PasswordValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordMatch(
    val message: String = "{com.alexgutjahr.constraint.PasswordMatch.fail}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class PasswordValidator : ConstraintValidator<PasswordMatch, RegistrationRequest> {
    override fun isValid(value: RegistrationRequest, context: ConstraintValidatorContext): Boolean {
        return value.password == value.confirmation
    }
}

@MustBeDocumented
@Constraint(validatedBy = [AgeValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Age(
    val min: Long,
    val message: String = "{com.alexgutjahr.constraint.Age.fail}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class AgeValidator : ConstraintValidator<Age, LocalDate> {

    private var min: Long? = null

    override fun initialize(age: Age) {
        this.min = age.min
    }

    override fun isValid(value: LocalDate, context: ConstraintValidatorContext): Boolean {
        return value.plusYears(min!!).isBefore(LocalDate.now())
    }

}

