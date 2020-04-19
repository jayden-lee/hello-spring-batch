package com.jayden.study.batch.domain

import com.jayden.study.batch.domain.enum.UserStatus
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var email: String? = null

    @Column
    var name: String? = null

    @Column
    var password: String? = null

    @Column
    @Enumerated(value = EnumType.STRING)
    var status: UserStatus? = null

    @Column
    var createdAt: LocalDateTime? = null

    @Column
    var updatedAt: LocalDateTime? = null

    fun setInactive(): User {
        status = UserStatus.INACTIVE
        return this
    }
}
