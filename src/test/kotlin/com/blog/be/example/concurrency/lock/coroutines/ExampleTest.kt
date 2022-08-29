package com.blog.be.example.concurrency.lock.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class ExampleTest {

    private val oneDay = 86400000L
    private val halfDay = oneDay/2

    @ExperimentalCoroutinesApi
    @Test
    fun `기본 테스트 디스패처는 코루틴 스코프를 가장 마지막에 실행한다`() = runTest {
        println("test start")
        async {
            delay(oneDay)
            println("launch 1")
        }

        launch {
            println("launch 2")
        }

        println("test end")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `테스트의 모든 코루틴을 실행합니다`() = runTest {

        launch {
            println("launch 1")
        }

        launch {
            println("launch 2")
        }

        advanceUntilIdle()

        println("test end")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `테스트를 가상시간 만큼 진행시킵니다`() = runTest {

        launch {
            delay(oneDay*2)
            println("launch 1")
        }

        launch {
            delay(halfDay)
            println("launch 2")
        }

        advanceTimeBy(oneDay)

        println("test end")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Unconfined은 모든 코루틴을 생성 즉시 실행합니다`() = runTest(UnconfinedTestDispatcher()) {
        println("test start")

        launch {
            delay(oneDay)
            println("launch 1")
        }

        launch {
            println("launch 2")
        }

        println("test end")
    }

}