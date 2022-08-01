package com.blog.be.example.concurrency.lock


class SynchronizedAndReentrantLock {
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