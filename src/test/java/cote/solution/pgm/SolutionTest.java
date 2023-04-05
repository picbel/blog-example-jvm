package cote.solution.pgm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        int curTime = 0;
        String curTask = "";
        Stack<String> pausedTasks = new Stack<>();
        for (String[] task : plans) {
            int startTime = timeToInt(task[1]);
            int endTime = startTime + Integer.parseInt(task[2]);
            if (curTime >= endTime) {
                answer.add(task[0]);
                if (!pausedTasks.isEmpty()) {
                    curTask = pausedTasks.pop();
                    endTime = curTime + timeToInt(plans[findTaskIndex(plans, curTask)][2]);
                } else {
                    curTask = "";
                }
            } else {
                if (!pausedTasks.isEmpty()) {
                    curTask = pausedTasks.pop();
                    endTime = curTime + timeToInt(plans[findTaskIndex(plans, curTask)][2]);
                }
                if (startTime >= curTime) {
                    if (!curTask.equals("")) {
                        pausedTasks.push(curTask);
                    }
                    curTask = task[0];
                    curTime = startTime;
                } else {
                    pausedTasks.push(task[0]);
                }
            }
        }
        if (!curTask.equals("")) {
            answer.add(curTask);
        }
        while (!pausedTasks.isEmpty()) {
            answer.add(pausedTasks.pop());
        }
        return answer.toArray(new String[0]);
    }

    private int timeToInt(String time) {
        String[] parts = time.split(":");
        if (parts.length != 2) {
            return -1; // 입력이 올바른 형식이 아님
        }
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    private int findTaskIndex(String[][] plans, String taskName) {
        for (int i = 0; i < plans.length; i++) {
            if (plans[i][0].equals(taskName)) {
                return i;
            }
        }
        return -1;
    }
}



public class SolutionTest {

    @Test
    public void testSolution() {
        Solution s = new Solution();
        String[][] plans1 = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[] expected1 = {"korean", "english", "math"};
        assertArrayEquals(expected1, s.solution(plans1));

        String[][] plans2 = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        String[] expected2 = {"science", "history", "computer", "music"};
        assertArrayEquals(expected2, s.solution(plans2));

        String[][] plans3 = {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
        String[] expected3 = {"bbb", "ccc", "aaa"};
        assertArrayEquals(expected3, s.solution(plans3));
    }
}
