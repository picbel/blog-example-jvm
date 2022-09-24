package cote.solution

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Programmers42579 {

    // 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
    // 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
    // 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val musics = genres.mapIndexed() { index, s ->
            Music(
                index = index,
                type = s,
                playbackCount = plays[index]
            )
        }.toList()

        // 많이 재생된 순서로 정렬
        musics
            .map { it.type }
            .toSet()
            .associateWith { mu-> musics.filter { it.type == mu }.sumOf { m -> m.playbackCount } }
            .toList()
            .sortedByDescending { it.second }
            .forEach { sortType ->
                musics.filter { it.type == sortType.first }
                    .sortedByDescending {
                        it.playbackCount
                    }
                    .take(2).forEach {
                    answer.add(it.index)
                }
            }

        return answer.toIntArray()
    }

    private data class Music(
        val index: Int,
        val type: String,
        val playbackCount: Int
    )

    fun verify(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                arrayOf("classic", "pop", "classic", "classic", "pop"), intArrayOf(500, 600, 150, 800, 2500),
                intArrayOf(4, 1, 3, 0)
            ),
        )
    }

    @MethodSource
    @ParameterizedTest
    fun verify(genres: Array<String>, plays: IntArray, answer: IntArray) {
        MatcherAssert.assertThat(solution(genres, plays), Matchers.`is`(answer))
    }


}