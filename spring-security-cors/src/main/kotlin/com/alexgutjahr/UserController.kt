package com.alexgutjahr

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost"])
class UserController {

    @GetMapping("/me")
    fun me(): String {
        return "John Doe"
    }

}
