package cote.solution.pgm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PG477859_2 {

    @Test
    void testSolution() {
        Solution2 s = new Solution2();
        assertEquals(2, s.solution(10,40,5));
    }
    class Solution {
        public int solution(int x, int y, int n) {
            if (x > y) return -1;

            int[] dp = new int[y + 1];
            for (int i = 0; i <= y; i++) {
                dp[i] = Integer.MAX_VALUE;
            }
            dp[x] = 0;

            for (int i = x; i < y; i++) {
                if (dp[i] != Integer.MAX_VALUE) {
                    if (i + n <= y) {
                        dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
                    }
                    if (i * 2 <= y) {
                        dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
                    }
                    if (i * 3 <= y) {
                        dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
                    }
                }
            }

            return dp[y] == Integer.MAX_VALUE ? -1 : dp[y];
        }
    }

    /**
     * 자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.
     *
     * x에 n을 더합니다
     * x에 2를 곱합니다.
     * x에 3을 곱합니다.
     * 자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요. 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
     */
    class Solution2 {
        public int solution(int x, int y, int n) {
            Queue<Temp> q = new LinkedList<>();
            Map<Integer,Temp> memo = new HashMap<>();
            q.add(new Temp(x,0));

            while (!q.isEmpty()) {
                Temp cur = q.poll();

                if (cur.value == y) return cur.count;

                if (cur.value + n <= y) {
                    if (!memo.containsKey(cur.value + n)) {
                        Temp temp = new Temp(cur.value + n, cur.count + 1);
                        memo.put(temp.value, temp);
                        q.add(temp);
                    }
                }

                if (cur.value * 2 <= y) {
                    if (!memo.containsKey(cur.value * 2)) {
                        Temp temp = new Temp(cur.value * 2, cur.count + 1);
                        memo.put(temp.value, temp);
                        q.add(temp);
                    }
                }

                if (cur.value * 3 <= y) {
                    if (!memo.containsKey(cur.value * 3)) {
                        Temp temp = new Temp(cur.value * 3, cur.count + 1);
                        memo.put(temp.value, temp);
                        q.add(temp);
                    }
                }
            }
            return -1;
        }

        // 테스트 용이라 굳이 게터 세터는 만들지 않습니다.
        private class Temp {
            int value;
            int count;

            public Temp(int value, int count) {
                this.value = value;
                this.count = count;
            }
        }
    }
}
