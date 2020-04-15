package com.jayden.study.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBatchApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBatchApplication>(*args)
}
