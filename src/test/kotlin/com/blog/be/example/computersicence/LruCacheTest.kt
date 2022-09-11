package com.blog.be.example.computersicence

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LruCacheTest {
    private lateinit var maxLruCache: LruCache<String, Int>

    @BeforeEach
    fun setup() {
        maxLruCache = LruCache(maxSize = 5)
        maxLruCache["1st"] = 1
        maxLruCache["2nd"] = 2
        maxLruCache["3rd"] = 3
        maxLruCache["4th"] = 4
        maxLruCache["5th"] = 5
    }

    @Test
    fun `maxsize까지 입력된 캐시에 데이터를 넣습니다`(){
        //given //when
        maxLruCache["6th"] = 6
        //then 가장 마지막으로 접근한 1번 데이터가 사라지고 6번데이터가 입력됩니다
        assertAll(
            { assertThat(maxLruCache.size,`is`(5)) },
            { assertThat(maxLruCache["1st"],`is`(nullValue())) },
        )
    }

    @Test
    fun `lru캐시는 데이터를 삭제시 가장 마지막에 접근한 삭제합니다`(){
        //given
        maxLruCache["1st"]
        //when
        maxLruCache["6th"] = 6
        //then 1번데이터를 한번 조회하여 head순서를 head 6-1-5-4-3-2로 업데이트합니다. 마지막 2번이 삭제됩니다.
        assertAll(
            { assertThat(maxLruCache.size,`is`(5)) },
            { assertThat(maxLruCache["2nd"],`is`(nullValue())) },
        )
    }
}