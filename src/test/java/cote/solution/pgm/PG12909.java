package cote.solution.pgm;

public class PG12909 {
    class Solution {

        private final char openChar = '(';
        boolean solution(String s) {
            int openCharCnt = 0;
            for (Character c : s.toCharArray()) {
                if (c.equals(openChar)) {
                    openCharCnt++;
                } else {
                    if (openCharCnt == 0) return false;
                    if (openCharCnt > 0) {
                        openCharCnt--;
                    }
                }
            }
            return openCharCnt == 0;
        }
    }
}
