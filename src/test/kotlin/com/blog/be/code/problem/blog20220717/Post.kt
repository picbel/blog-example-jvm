package com.blog.be.code.problem.blog20220717

import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


data class ServiceKey(
    private val key: String
)

class ServiceManager(
    private val serviceMap: ConcurrentHashMap<ServiceKey, String>
) {
    fun get(key: String): String {
        return serviceMap[ServiceKey(key)]!!
    }

    fun isWorking(key: String): Boolean {
        return serviceMap.containsKey(ServiceKey(key))
    }

    fun execute(key: String, value: Int) = serviceMap.set(ServiceKey(key), "Something Do! $value")
}

class ServiceManagerTest {

    private val sut: ServiceManager = ServiceManager(ConcurrentHashMap())

    @Test
    fun `여러 쓰레드에서 isWorkingServiceManager을 호출합니다`() {
        val numberOfThreads = 10
        val service: ExecutorService = Executors.newFixedThreadPool(100)
        val latch = CountDownLatch(numberOfThreads)

        // 1번 실행
        service.execute {
            sut.execute("1", 1)
        }


        for (i in 0 until numberOfThreads) {
            service.execute() {
                sut.isWorking("1")
                    .also { println("${Thread.currentThread().name} = $it") }
                latch.countDown()
            }
        }

        latch.await()

    }

    @Test
    fun `여러 쓰레드에서 isWorkingServiceManager을 호출합니다 - 2`() {
        val numberOfThreads = 10
        val service: ExecutorService = Executors.newFixedThreadPool(100)
        val latch = CountDownLatch(numberOfThreads)

        // 실행
        service.execute {
            sut.execute("1", 1)
        }
        Thread.sleep(100)

        for (i in 0 until numberOfThreads) {
            if (i == 5) {
                service.execute {
                    sut.execute("1", i)
                }
            }
            service.execute() {
                sut.get("1")
                    .also { println("${Thread.currentThread().name} = $it") }
                latch.countDown()
            }
        }

        latch.await()

    }


}