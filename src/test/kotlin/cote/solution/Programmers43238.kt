package cote.solution

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Programmers43238 {

    fun solution(n: Int, edge: Array<IntArray>): Int {
        var answer = 0
        return answer
    }

    fun verify(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(),
            Arguments.of(),
        )
    }

    @MethodSource
    @ParameterizedTest
    fun verify() {
        MatcherAssert.assertThat(solution(), Matchers.`is`(answer))
    }
}

