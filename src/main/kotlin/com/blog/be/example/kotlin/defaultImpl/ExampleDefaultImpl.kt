package com.blog.be.example.kotlin.defaultImpl

import org.springframework.transaction.annotation.Transactional

interface AAA {

    fun doSomething(){
        println("AAA method doSomething")
        doTransactionSomething()
    }

    fun doTransactionSomething()
}

open class AAAImpl : AAA {


    @Transactional
    override fun doTransactionSomething() {
        println("doTransactionSomething")
    }

}
