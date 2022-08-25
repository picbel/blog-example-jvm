package cote.solution

import org.junit.jupiter.api.Test

interface VersionControl {
    fun isBadVersion(version: Int): Boolean = version >= 4
}

class Solution : VersionControl{
    @Test
    fun firstBadVersion()  {
        var start = 1
        var last = 5

        while (start <= last){
            var mid = start + (last-start)/2 // 이진탐색에서 중간값을 구하는 방법이다
            println("mid = $mid")
            if (isBadVersion(mid)){
                last = mid -1
            }else{
                start = mid +1
            }
            println("start = $start")
            println("last = $last")
            println("===")
        }
        println("= end =")
        println("return $start")
    }
}