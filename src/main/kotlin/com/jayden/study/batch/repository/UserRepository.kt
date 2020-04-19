package com.jayden.study.batch.repository

import com.jayden.study.batch.domain.User
import com.jayden.study.batch.domain.enum.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface UserRepository : JpaRepository<User, Long> {

    fun findByUpdatedAtBeforeAndStatusEquals(localDateTime: LocalDateTime, status: UserStatus): List<User>
}
