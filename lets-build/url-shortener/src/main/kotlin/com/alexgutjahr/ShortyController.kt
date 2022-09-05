package com.alexgutjahr

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ShortyController(private val service: ShortyService) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun shorten(@RequestBody request: ShortyRequest): ShortyResponse {
    val hash = service.shorten(request.url)
    return ShortyResponse(hash)
  }

  @GetMapping("/{hash}")
  fun resolve(@PathVariable hash: String): ResponseEntity<HttpStatus> {
    val target = service.resolve(hash)

    return ResponseEntity
      .status(HttpStatus.MOVED_PERMANENTLY)
      .location(URI.create(target))
      .header(HttpHeaders.CONNECTION, "close")
      .build()
  }

}

data class ShortyRequest(val url: String)
data class ShortyResponse(val hash: String)
