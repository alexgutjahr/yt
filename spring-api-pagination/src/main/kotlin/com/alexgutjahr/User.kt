package com.alexgutjahr

import org.hibernate.Hibernate
import org.hibernate.annotations.NaturalId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

data class User(val id: String, val name: String)

@Entity
@Table(name = "users")
class UserEntity(@Id @NaturalId val id: String) {

    lateinit var name: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    fun asUser(): User = User(id, name)

}
