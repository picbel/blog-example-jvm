package com.blog.be.example.concurrency.lock

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


class SynchronizedExample {
    @Synchronized
    fun syncMethod() = run { }

    fun syncStatements() {
        synchronized(this) {
            doSomething()
        }
        doSomething2()
    }

    private fun doSomething() {}

    private fun doSomething2() {}

}

class ReentrantLockExample{
    private val lock: ReentrantLock = ReentrantLock(true)

    fun foo(){
        try {
            lock.lock()
            doSomething()
        }finally {
            lock.unlock()
        }

    }
    private fun doSomething() {}

    fun foo2(){
        lock.withLock {

        }
    }

}