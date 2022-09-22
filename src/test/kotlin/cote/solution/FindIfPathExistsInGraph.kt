package cote.solution

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll


class FindIfPathExistsInGraph {
    class Solution {
        @Test
        fun expect() {
            assertAll(
                {
                    assertThat(
                        validPath(
                            n = 3,
                            edges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)),
                            source = 0,
                            destination = 2
                        ),
                        `is`(true)
                    )
                },
                {
                    assertThat(
                        validPath(
                            n = 6,
                            edges = arrayOf(
                                intArrayOf(0, 1),
                                intArrayOf(0, 2),
                                intArrayOf(3, 5),
                                intArrayOf(5, 4),
                                intArrayOf(4, 3)
                            ),
                            source = 0,
                            destination = 5
                        ),
                        `is`(false)
                    )
                },
                {
                    assertThat(
                        validPath(
                            n = 5,
                            edges = arrayOf(
                                intArrayOf(0, 4),
                            ),
                            source = 0,
                            destination = 4
                        ),
                        `is`(true)
                    )
                },
                {
                    assertThat(
                        validPath(
                            n = 0,
                            edges = arrayOf(
                            ),
                            source = 0,
                            destination = 0
                        ),
                        `is`(true)
                    )
                },
                {
                    assertThat(
                        validPath(
                            n = 10,
                            edges = arrayOf(
                                intArrayOf(4, 3),
                                intArrayOf(1, 4),
                                intArrayOf(4, 8),
                                intArrayOf(1, 7),
                                intArrayOf(6, 4),
                                intArrayOf(4, 2),
                                intArrayOf(7, 4),
                                intArrayOf(4, 0),
                                intArrayOf(0, 9),
                                intArrayOf(5, 4),
                            ),
                            source = 5,
                            destination = 9
                        ),
                        `is`(true)
                    )
                },
            )
        }


        fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
            if (source == destination) return true

            val map = mutableMapOf<Int, MutableList<Int>>()
            edges.forEach {
                map[it[0]] = map.getOrDefault(it[0], mutableListOf()).apply { add(it[1]) }
                map[it[1]] = map.getOrDefault(it[1], mutableListOf()).apply { add(it[0]) }
            }

            val nextPath = mutableListOf<Int>()
            val prePath = mutableSetOf<Int>()
            nextPath.add(source)
            while (nextPath.isNotEmpty()) {
                val current = nextPath.removeAt(0)
                if (prePath.contains(current)) continue
                prePath.add(current)
                val neighbors: List<Int> = (map[current] ?: return false)
                for (item in neighbors) {
                    if (item == destination) return true
                    nextPath.add(item)
                }
            }

            return false
        }

    }
}
