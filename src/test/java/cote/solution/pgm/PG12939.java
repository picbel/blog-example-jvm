package cote.solution.pgm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PG12939 {
    @Test
    public void verify() {
        Solution s = new Solution();
        System.out.println(s.solution("1 2 3 4").equals("1 4"));
        System.out.println(s.solution("-1 -2 -3 -4").equals("-4 -1"));
        System.out.println(s.solution("-1 -1").equals("-1 -1"));
    }
    class Solution {

        public String solution(String s) {
            List<Integer> list = Arrays.stream(s.split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            return Collections.min(list) + " " + Collections.max(list);
        }
    }
}
