package com.alexgutjahr

import org.hibernate.Hibernate
import org.hibernate.annotations.NaturalId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id @NaturalId
    val email: String? = null
) {

    lateinit var name: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return email == other.email
    }

    override fun hashCode(): Int = email.hashCode()

    override fun toString() = "$name <$email>"

}
