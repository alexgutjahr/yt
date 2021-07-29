package com.alexgutjahr

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.server.ServerWebExchange
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import org.thymeleaf.spring5.context.webflux.SpringWebFluxContext
import java.io.ByteArrayOutputStream

@SpringBootApplication
class SpringPdfApplication

fun main(args: Array<String>) {
    runApplication<SpringPdfApplication>(*args)
}

@Controller
class PdfController(private val engine: SpringWebFluxTemplateEngine) {

  @GetMapping("/html")
  suspend fun html(): String {
    return "letter"
  }

  @GetMapping("/pdf")
  suspend fun pdf(exchange: ServerWebExchange): ResponseEntity<ByteArray> {
    val context = SpringWebFluxContext(exchange)
    val html = engine.process("letter", context)
    val pdf = ByteArrayOutputStream()

    HtmlConverter.convertToPdf(html, pdf, ConverterProperties().apply {
      baseUri = "http://localhost:8080"
    })

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=letter.pdf")
      .contentType(MediaType.APPLICATION_PDF)
      .body(pdf.toByteArray())
  }

}
