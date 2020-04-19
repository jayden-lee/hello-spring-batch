package com.jayden.study.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class HelloSpringBatchApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBatchApplication>(*args)
}
