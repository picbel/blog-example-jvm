package com.blog.be.example.kotlin.defaultImpl

import org.junit.jupiter.api.Test

internal class AAAImplTest {

    @Test
    fun callAAA(){
        val sut : AAA = AAAImpl()

        sut.doSomething()
    }
}