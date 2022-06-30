package com.blog.be

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JvmApplication

fun main(args: Array<String>) {
    runApplication<JvmApplication>(*args)
}
