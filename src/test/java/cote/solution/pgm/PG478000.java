package cote.solution.pgm;

public class PG478000 {

    /**
     * #문제설명
     *
     * 집에서 학교를 가려고 하는데 중간에 물에 잠기지 않은 지역을 통해 학교에 가려해
     * 집에서 학교까지 가는길은 m*n의 크기의 격자 모양으로 나타낼수있어
     *
     * 격자의 크기 m, n과 물이 잠긴지역의 좌표를 담은 2차원 배열 puddles가 매개변수로 주어질때
     * 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단 경로르의 갯수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.
     *
     * # 제한 사항
     * 격자의 크기는 1 이상 100이하의 자연수야
     * 둘다 1인경우의입력은 주어지지않는다
     * 물에 잠긴 지역은 0개 이상 10개 이하야
     * 집과 학교가 모두 물에잠긴경우는 주어지지않아
     *
     * # 입출력 예
     * m = 4, n = 3, puddles = [[2,2]]  return = 4
     */
    public class Solution {
        static final int MOD = 1_000_000_007;

        public int solution(int m, int n, int[][] puddles) {
            int[][] dp = new int[n + 1][m + 1];
            for (int[] puddle : puddles) {
                dp[puddle[1]][puddle[0]] = -1;
            }

            dp[1][1] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (i == 1 && j == 1 || dp[i][j] == -1) {
                        continue;
                    }

                    if (dp[i - 1][j] != -1) {
                        dp[i][j] += dp[i - 1][j] % MOD;
                    }

                    if (dp[i][j - 1] != -1) {
                        dp[i][j] += dp[i][j - 1] % MOD;
                    }
                }
            }

            return dp[n][m] % MOD;
        }
    }
}

