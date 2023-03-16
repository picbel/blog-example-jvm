package cote.solution

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TopKFrequentElements {
    @Test
    fun expect() {
        val sol = Solution()
        assertAll(
            { sol.topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).sorted() shouldBe intArrayOf(1, 2) },
            { sol.topKFrequent(intArrayOf(1), 1).sorted() shouldBe intArrayOf(1) },
            { sol.topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3, 4, 4, 4, 4, 4), 2).sorted() shouldBe intArrayOf(1, 4) },
        )
    }

    class Solution {

        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val counter : MutableMap<Int,Int> = mutableMapOf()

            // n
            nums.forEach {
                counter[it] = counter.getOrDefault(it , 0) + 1
            }

            val min = counter.values.sortedByDescending { it }.take(k).minOf { it }
            return counter.entries.filter { it.value >= min }.map { it.key }.toIntArray()
        }
    }
}
