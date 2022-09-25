package cote.solution

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Palindrome {

    fun solution(str: String): Boolean {
        return str.reversed() == str
    }

    /*
     * 양쪽 인덱스를 -1씩하며 비교
     */
    fun solution2(str: String): Boolean {
        val mid = str.length / 2
        for (i in str.indices) {
            if (i == mid) break

            if (str[i] != str[str.length - i - 1]) return false
        }

        return true
    }

    fun verify(): Stream<Arguments> {
        return Stream.of(
            Arguments.of("aba", true),
            Arguments.of("abba", true),
            Arguments.of("abcba", true),
            Arguments.of("abc", false),
            Arguments.of("abcabc", false),
            Arguments.of("기러기", true),
            Arguments.of("기러러기", true),
            Arguments.of("기기기기기기기기기기기러러러기기기기기기기기기기기", true),
            Arguments.of("기기러러기", false),
        )
    }

    @MethodSource
    @ParameterizedTest
    fun verify(str: String, answer: Boolean) {
        MatcherAssert.assertThat(solution(str), Matchers.`is`(answer))
        MatcherAssert.assertThat(solution2(str), Matchers.`is`(answer))
    }
}

