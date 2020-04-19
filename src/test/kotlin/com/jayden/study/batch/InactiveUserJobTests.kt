package com.jayden.study.batch

import com.jayden.study.batch.domain.enum.UserStatus
import com.jayden.study.batch.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
class InactiveUserJobTests {

    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `휴면 회원 전환 테스트`() {
        val jobExecution = jobLauncherTestUtils.launchJob()

        assertEquals(BatchStatus.COMPLETED, jobExecution.status)
        assertEquals(0, userRepository.findByUpdatedAtBeforeAndStatusEquals(LocalDateTime.now().minusYears(1), UserStatus.ACTIVE).size)
    }
}
