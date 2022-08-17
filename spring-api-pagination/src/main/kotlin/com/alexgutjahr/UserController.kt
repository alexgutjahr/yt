package com.alexgutjahr

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val repo: UserRepository) {

    @GetMapping
    fun all(
        @RequestParam("page") page: Optional<Int>,
        @RequestParam("size") size: Optional<Int>
    ): CollectionRessource {
        val result = repo.findAll(PageRequest.of(page.orElse(0), size.orElse(5)))
        return CollectionRessource(result.totalElements, result.totalPages, result.content.map { it.asUser() })
    }

}

data class CollectionRessource(val elements: Long, val pages: Int, val items: List<*>)
