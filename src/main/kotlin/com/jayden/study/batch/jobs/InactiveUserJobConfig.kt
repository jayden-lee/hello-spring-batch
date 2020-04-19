package com.jayden.study.batch.jobs

import com.jayden.study.batch.domain.User
import com.jayden.study.batch.domain.enum.UserStatus
import com.jayden.study.batch.jobs.reader.QueueItemReader
import com.jayden.study.batch.repository.UserRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class InactiveUserJobConfig {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Bean
    fun inactiveUserJob(jobBuilderFactory: JobBuilderFactory, inactiveJobStep: Step): Job {
        return jobBuilderFactory.get("inactiveUserJob")
            .preventRestart()
            .start(inactiveJobStep)
            .build()
    }

    @Bean
    fun inactiveJobStep(stepBuilderFactory: StepBuilderFactory): Step {
        return stepBuilderFactory.get("inactiveUserStep")
            .chunk<User, User>(10)
            .reader(inactiveUserReader())
            .processor(inactiveUserProcessor())
            .writer(inactiveUserWriter())
            .build()
    }

    @Bean
    @StepScope
    fun inactiveUserReader(): QueueItemReader<User> {
        return QueueItemReader(userRepository.findByUpdatedAtBeforeAndStatusEquals(LocalDateTime.now().minusYears(1), UserStatus.ACTIVE))
    }

    fun inactiveUserProcessor(): ItemProcessor<User, User> {
        return ItemProcessor<User, User> {
            it.setInactive()
        }
    }

    fun inactiveUserWriter(): ItemWriter<User> {
        return ItemWriter<User> {
            userRepository.saveAll(it)
        }
    }
}
