package cote.solution.pgm;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150367
 */
public class PG150367 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long[] numbers = {7, 42, 5};
        int[] result = solution.solution(numbers);
        System.out.println(Arrays.toString(result));  // [1, 1, 0]

        numbers = new long[]{63, 111, 95};
        result = solution.solution(numbers);
        System.out.println(Arrays.toString(result));  // [1, 1, 0]
    }

    static class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                int nodeCount = getNodeCount(getDepth(numbers[i]));
                String binaryString = getBinaryString(numbers[i], nodeCount);
                long decimalNumber = Long.parseLong(binaryString, 2);
                if (decimalNumber == numbers[i]) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            }

            return answer;
        }

        private static int getDepth(long number) {
            int depth = 0;
            while (number > 0) {
                depth++;
                number /= 2;
            }
            return depth;
        }

        private static int getNodeCount(int depth) {
            if (depth == 0) {
                return 0;
            } else {
                return (int) Math.pow(2, depth - 1) + getNodeCount(depth - 1) * 2;
            }
        }

        private static String getBinaryString(long number, int nodeCount) {
            StringBuilder binaryString = new StringBuilder();
            for (int i = nodeCount - 1; i >= 1; i--) {
                if (i < nodeCount - (number - 1)) {
                    binaryString.append('0');
                } else {
                    binaryString.append('1');
                }
            }
            return binaryString.toString();
        }
    }
}

