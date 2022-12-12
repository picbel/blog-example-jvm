package cote.solution.pgm

import org.junit.jupiter.api.Test

class Solution136798 {

    @Test
    fun verify() {
        assert(solution(5, 3, 2) == 10)
        println("===")
        assert(solution(10, 3, 2) == 21)
    }

    fun solution(number: Int, limit: Int, power: Int): Int {
        return (1..number).sumOf { n ->
            getMeasure(n).run {
                return@run this.takeIf { it <= limit } ?: power
            }
        }
    }

    fun getMeasure(n: Int): Int {
        var cnt = 0

        var i = 1
        while (i * i <= n) {
            if (n % i == 0) {
                cnt++
                if (i * i < n) {
                    cnt++
                }
            }
            i++
        }
        return cnt
    }
}
