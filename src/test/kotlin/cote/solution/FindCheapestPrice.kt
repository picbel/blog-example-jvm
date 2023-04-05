package cote.solution

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class FindCheapestPrice {
    @Test
    fun verify() {
        val solution = Solution()
        solution.findCheapestPrice(
            n = 4,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(2, 0, 100),
                intArrayOf(1, 3, 600),
                intArrayOf(2, 3, 200),
            ),
            src = 0, dst = 3, k = 1
        ) shouldBe 700
        solution.findCheapestPrice(
            n = 3,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500),
            ),
            src = 0, dst = 2, k = 1
        ) shouldBe 200
        solution.findCheapestPrice(
            n = 3,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500),
            ),
            src = 0, dst = 2, k = 0
        ) shouldBe 500
    }

    @Test
    fun verify2() {
        val solution = Solution2()
        solution.findCheapestPrice(
            n = 4,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(2, 0, 100),
                intArrayOf(1, 3, 600),
                intArrayOf(2, 3, 200),
            ),
            src = 0, dst = 3, k = 1
        ) shouldBe 700
        solution.findCheapestPrice(
            n = 3,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500),
            ),
            src = 0, dst = 2, k = 1
        ) shouldBe 200
        solution.findCheapestPrice(
            n = 3,
            flights = arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500),
            ),
            src = 0, dst = 2, k = 0
        ) shouldBe 500
    }

    private class Solution {
        fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
            // index from [ key(to) to value(price)  ]
            val cities: Array<MutableMap<Int, Int>> = Array(n) { mutableMapOf<Int, Int>() }
            flights.forEach {
                cities[it[0]][it[1]] = it[2]
            }
            return calPrice(src, dst, k, 0, 0, cities)
        }

        private fun calPrice(
            src: Int,
            dst: Int,
            k: Int,
            stationCount: Int,
            price: Int,
            cities: Array<MutableMap<Int, Int>>
        ): Int {
            if (src == dst) return price.also { println("src == dst $price") }
            if (k < stationCount) return 0
            cities[src].map {
                calPrice(src = it.key, dst, k, stationCount + 1, price = price + it.value, cities)
            }.run {
                return this.filter { it > 0 }.takeIf { it.isNotEmpty() }?.minOf { it } ?: -1
            }
        }

    }

    private class Solution2 {

        fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
            // 인접 리스트 생성
            val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
            for (flight in flights) {
                adj[flight[0]].add(Pair(flight[1], flight[2]))
            }

            // 우선순위 큐를 이용한 다익스트라 알고리즘
            val pq = PriorityQueue<Node>(compareBy { it.cost })
            pq.offer(Node(src, 0, 0))

            // minCost 배열 초기화
            val minCost = Array(n) { IntArray(k + 2) { Int.MAX_VALUE } }
            minCost[src][0] = 0

            while (pq.isNotEmpty()) {
                val node = pq.poll()

                // 목적지에 도달한 경우 최소 비용 반환
                if (node.u == dst) {
                    return node.cost
                }

                // 인접한 노드들에 대해 최소 비용 업데이트
                for (v in adj[node.u]) {
                    val (next, cost) = v

                    // 방문한 노드의 수가 k보다 작거나 같고, 새로운 비용이 현재 비용보다 작은 경우에만 업데이트
                    if (node.stops <= k && node.cost + cost < minCost[next][node.stops + 1]) {
                        minCost[next][node.stops + 1] = node.cost + cost
                        pq.offer(Node(next, node.cost + cost, node.stops + 1))
                    }
                }
            }

            // 소스에서 목적지까지 k번 이하로 방문할 수 있는 경로가 없는 경우 -1 반환
            return -1
        }

        data class Node(val u: Int, val cost: Int, val stops: Int)
    }
}