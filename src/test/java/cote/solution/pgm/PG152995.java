package cote.solution.pgm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PG152995 {

    class Solution {
        public int solution(int[][] scores) {
            int[] wanho = scores[0];
            int total = scores.length;
            int wanhoSum = wanho[0] + wanho[1];
            int wanhoRank = 1;
            boolean canGetIncentive = true;

            for (int i = 1; i < total; i++) {
                int[] current = scores[i];
                if (current[0] > wanho[0] && current[1] > wanho[1]) {
                    canGetIncentive = false;
                    break;
                }
            }

            if (!canGetIncentive) {
                return -1;
            }

            for (int i = 1; i < total; i++) {
                int[] current = scores[i];
                int currentSum = current[0] + current[1];
                boolean currentCanGetIncentive = true;

                for (int j = 0; j < total; j++) {
                    if (i == j) {
                        continue;
                    }
                    int[] other = scores[j];
                    if (current[0] < other[0] && current[1] < other[1]) {
                        currentCanGetIncentive = false;
                        break;
                    }
                }

                if (currentCanGetIncentive && currentSum > wanhoSum) {
                    wanhoRank++;
                }
            }

            return wanhoRank;
        }
    }



    Solution solution = new Solution();

    @Test
    void testSolution() {
        assertEquals(4, solution.solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}));
    }

}
