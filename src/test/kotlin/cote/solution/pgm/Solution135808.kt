package cote.solution.pgm

import org.junit.jupiter.api.Test

class Solution135808 {

    @Test
    fun verify() {
        assert(solution(3, 4, intArrayOf(1, 2, 3, 1, 2, 3, 1)) == 8)
        assert(solution(4, 3, intArrayOf(4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2)) == 33)

    }

    fun solution(k: Int, m: Int, score: IntArray): Int {
        return score.run { sortedDescending() }.toList().chunked(m).filter { it.size == m }.sumOf { it.minOf { v -> v } * m }
    }

}
