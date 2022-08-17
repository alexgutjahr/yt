package com.alexgutjahr

import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity {

    @Id @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    val id: UUID? = null

    lateinit var name: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString() = "$id - $name"

}
