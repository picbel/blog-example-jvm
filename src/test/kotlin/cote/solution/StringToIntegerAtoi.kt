package cote.solution

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class StringToIntegerAtoi {

    private val numberPool = ('0'..'9')

    private val pm = listOf<Char>('+', '-')

    private val intPool = pm + numberPool

    fun myAtoi(s: String): Int {
        val n = StringBuffer()
        (s.takeIf { it.isNotBlank() }?.trim().takeIf { intPool.contains(it?.first()) } ?: return 0).let {
            var pmFlag  = false
            run{
                it.forEach { num ->
                    if (num == '.') {
                        return@run
                    }
                    if (pm.contains(num)) {
                        if (pmFlag) { return  0 }
                        pmFlag = true
                        n.append(num)
                    }
                    if (numberPool.contains(num)) {
                        n.append(num)
                    }
                }
            }
        }
        return try {
            if (n.length < 2 && pm.contains(n.first())) {
                0
            } else {
                n.toString().toInt()
            }
        } catch (e: NumberFormatException) {
            if (n.first() == '-') {
                Int.MIN_VALUE
            } else {
                Int.MAX_VALUE
            }
        }
    }

    @Test
    fun verify() {
        assertAll(
            { assert(myAtoi("42") == 42) },
            { assert(myAtoi("   -42") == -42) },
            { assert(myAtoi("4193 with words") == 4193) },
//            { assert(myAtoi("-4444") == -4444) },
            { assert(myAtoi("words and 987") == 0) },
            { assert(myAtoi("0032") == 32) },
            { assert(myAtoi("3.14159") == 3) },
            { assert(myAtoi(".1") == 0) },
            { assert(myAtoi("+-12") == 0) },
            { assert(myAtoi("+") == 0) },
            { assert(myAtoi("00000-42a1234") == 0) },
        )
    }

//    fun verify(): Stream<Arguments> {
//        return Stream.of(
//            Arguments.of(intArrayOf(2, 1, 3, 2), 2, 1),
//            Arguments.of(intArrayOf(1, 1, 9, 1, 1, 1), 0, 5),
//        )
//    }
//
//    @MethodSource
//    @ParameterizedTest
//    fun verify(priorities: IntArray, location: Int, answer: Int) {
//        MatcherAssert.assertThat(solution(priorities, location), Matchers.`is`(answer))
//    }
}