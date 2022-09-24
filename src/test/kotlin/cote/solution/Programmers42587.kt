package cote.solution

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Programmers42587 {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        // pair<인덱스, 중요도>
        val q : Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        priorities.mapIndexed { index, i -> Pair(index, i) }.forEach {
            q.add(it)
        }
        val taskSeqs = priorities.sortedDescending().toMutableList()
        while (q.isNotEmpty()) {
            val paper = q.poll()
            if (paper.second == taskSeqs.first()) {
                println("hit")
                answer++
                taskSeqs.removeAt(0)
                if (paper.first == location) {
                    break
                }
            } else {
                q.add(paper)
            }
        }
        return answer
    }


    fun verify(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(intArrayOf(2, 1, 3, 2), 2, 1),
            Arguments.of(intArrayOf(1, 1, 9, 1, 1, 1), 0, 5),
        )
    }

    @MethodSource
    @ParameterizedTest
    fun verify(priorities: IntArray, location: Int, answer: Int) {
        MatcherAssert.assertThat(solution(priorities, location), Matchers.`is`(answer))
    }
}