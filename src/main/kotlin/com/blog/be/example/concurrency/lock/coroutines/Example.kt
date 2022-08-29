package com.blog.be.example.concurrency.lock.coroutines


import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


class Example {
    suspend fun onePlusTwo() = coroutineScope {
        val oneAsync = async {
            delay(1000)
            return@async 1
        }
        val twoAsync = async {
            delay(1000)
            return@async 2
        }
        oneAsync.await() + twoAsync.await()
    }
}