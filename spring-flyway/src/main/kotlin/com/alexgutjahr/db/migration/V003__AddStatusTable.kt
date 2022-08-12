package com.alexgutjahr.db.migration

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.springframework.stereotype.Component

@Component
class V003__AddStatusTable : BaseJavaMigration() {

    override fun migrate(context: Context) {
        context.connection.prepareStatement("CREATE TABLE status").execute()
    }

}
