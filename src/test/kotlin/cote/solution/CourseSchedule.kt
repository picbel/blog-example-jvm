package cote.solution

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class CourseSchedule {
    @Test
    fun verify() {
        val solution = Solution()
        solution.canFinish(2, arrayOf(intArrayOf(1, 0))) shouldBe true
        solution.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))) shouldBe false
    }

    @Test
    fun verify2() {
        val solution = Solution()
        solution.canFinish2(2, arrayOf(intArrayOf(1, 0))) shouldBe true
        solution.canFinish2(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))) shouldBe false
    }

    private class Solution {
        fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            TODO()
        }

        fun canFinish2(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            // 그래프의 인접 리스트 표현 생성
            val graph = Array(numCourses) { mutableListOf<Int>() }
            val inDegree = IntArray(numCourses)
            for (prerequisite in prerequisites) {
                graph[prerequisite[1]].add(prerequisite[0])
                inDegree[prerequisite[0]]++
            }

            // 진입 차수가 0인 모든 노드를 큐에 추가
            val queue: Queue<Int> = LinkedList()
            for (i in 0 until numCourses) {
                if (inDegree[i] == 0) {
                    queue.offer(i)
                }
            }

            // 위상 순서로 노드를 처리하며, 간선을 제거하고 진입 차수가 0인 새로운 노드를 큐에 추가
            while (queue.isNotEmpty()) {
                val node = queue.poll()
                for (neighbor in graph[node]) {
                    inDegree[neighbor]--
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor)
                    }
                }
            }

            // 모든 노드가 처리되었다면 true 반환
            return inDegree.sum() == 0
        }
    }
}
