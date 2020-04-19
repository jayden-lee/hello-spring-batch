package com.jayden.study.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableBatchProcessing
@Configuration
class TestJobConfig {

    @Bean
    fun jobLauncherTestUtils() : JobLauncherTestUtils {
        return JobLauncherTestUtils()
    }
}
