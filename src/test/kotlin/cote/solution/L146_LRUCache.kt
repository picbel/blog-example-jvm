package cote.solution

import org.junit.jupiter.api.Test

class L146_LRUCache {

    @Test
    fun solution() {
        val lRUCache = LRUCache(2)
        lRUCache.put(1, 1) // cache is {1=1}

        lRUCache.put(2, 2) // cache is {1=1, 2=2}

        lRUCache.get(1).also { print(">>>>> $it    "); println(it == 1) } // return 1

        lRUCache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}

        lRUCache.get(2).also { print(">>>>> $it    "); println(it == -1) } // returns -1 (not found)

        lRUCache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}

        lRUCache.get(1).also { print(">>>>> $it    "); println(it == -1) } // return -1 (not found)

        lRUCache.get(3).also { print(">>>>> $it    "); println(it == 3) } // return 3

        lRUCache.get(4).also { print(">>>>> $it    "); println(it == 4) } // return 4

    }
    @Test
    fun solution2() {
        val lRUCache = LRUCache(2)
        lRUCache.put(2, 1)
        lRUCache.put(1, 1)
        lRUCache.put(2, 3)
        lRUCache.put(4, 1)
        lRUCache.get(1).also { print(">>>>> $it    "); println(it == -1) } // return 3
        lRUCache.get(2).also { print(">>>>> $it    "); println(it == 3) } // return 4

    }

    @Test
    fun solution3() {
        val lRUCache = LRUCache(2)
        lRUCache.put(1, 0)
        lRUCache.put(2, 2)
        lRUCache.get(1).also { print(">>>>> $it    "); println(it == 0) } // return 3
        lRUCache.put(3, 3)
        lRUCache.get(2).also { print(">>>>> $it    "); println(it == -1) } // return 3
        lRUCache.put(4, 4)
        lRUCache.get(1).also { print(">>>>> $it    "); println(it == -1) } // return 4
        lRUCache.get(3).also { print(">>>>> $it    "); println(it == 3) } // return 4
        lRUCache.get(4).also { print(">>>>> $it    "); println(it == 4) } // return 4

    }

    // 일부러 LinkedHashMap은 안씀
    class LRUCache(val capacity: Int) {

        private val keySet: LinkedHashSet<Int> = LinkedHashSet()

        private val valMap = mutableMapOf<Int, Int>()

        fun get(key: Int): Int {

            return (valMap[key] ?: -1).also {
                if (it > -1) {
                    keySet.remove(key)
                    keySet.add(key)
                }
            }
        }

        fun put(key: Int, value: Int) {
            if (valMap.size < capacity) {
                putVal(key, value)
            } else {
                if (!valMap.containsKey(key)) {
                    keySet.removeFirst()?.also {
                        valMap.remove(it)
                    }
                }
                putVal(key, value)
            }
        }

        private fun putVal(key: Int, value: Int) {
            keySet.remove(key)
            keySet.add(key)
            valMap[key] = value
        }

        private fun LinkedHashSet<Int>.removeFirst(): Int? {
            return if (this.isEmpty()) {
                null
            } else {
                return this.first().also {
                    this.remove(it)
                }
            }
        }
    }
}


