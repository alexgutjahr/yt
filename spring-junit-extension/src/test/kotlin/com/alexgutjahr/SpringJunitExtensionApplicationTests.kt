package com.alexgutjahr

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.sql.DataSource

@SpringBootTest
@ExtendWith(DatabaseCleanerExtension::class)
class SpringJunitExtensionApplicationTests {

  @Autowired
  private lateinit var ds: DataSource

  @Test
  fun `get me some heroes`() {
    ds.connection.use { connection ->
      val heroes = listOf(
        Hero("Hulk", "Bruce", "Banner"),
        Hero("Spider-Man", "Peter", "Parker"),
        Hero("Iron Man", "Tony", "Stark"))

      heroes.forEach { hero ->
        connection
          .prepareStatement("INSERT INTO heroes (name, first_name, last_name) VALUES ('${hero.name}', '${hero.firstname}', '${hero.lastname}')")
          .execute()
      }
    }
  }

}

data class Hero(val name: String, val firstname: String, val lastname: String)

class DatabaseCleanerExtension : AfterEachCallback {

  override fun afterEach(context: ExtensionContext) {
    val ds = SpringExtension.getApplicationContext(context).getBean(DataSource::class.java)

    ds.connection.use { connection ->
      connection.prepareStatement("DELETE FROM heroes").execute()
    }
  }

}
