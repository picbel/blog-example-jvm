package cote.solution.pgm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/154540
 */
public class PG154540 {
    @Test
    public void test1() {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        int[] expected = {1, 1, 27};
        int[] result = new Solution().solution(maps);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void test2() {
        String[] maps = {"XXX","XXX","XXX"};
        int[] expected = {-1};
        int[] result = new Solution().solution(maps);
        Assertions.assertArrayEquals(expected, result);
    }
    class Solution {
        int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited;
        int[][] map;
        int n, m;

        public int[] solution(String[] maps) {
            n = maps.length;
            m = maps[0].length();
            visited = new boolean[n][m];
            map = new int[n][m];

            // maps 배열을 이차원 int 배열로 변환
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    char c = maps[i].charAt(j);
                    if(c == 'X') map[i][j] = -1; // 바다인 경우
                    else map[i][j] = c - '0'; // 땅인 경우
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(!visited[i][j] && map[i][j] > 0) { // 땅이고 방문하지 않았다면
                        list.add(bfs(i, j)); // bfs 실행 후 list에 추가
                    }
                }
            }

            if(list.isEmpty()) { // 땅이 없는 경우
                return new int[]{-1};
            } else {
                Collections.sort(list); // list 오름차순 정렬
                return list.stream().mapToInt(i -> i).toArray(); // ArrayList를 int[]로 변환 후 반환
            }
        }

        public int bfs(int x, int y) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            visited[x][y] = true;

            int result = 0;
            while(!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                result += map[currX][currY]; // 현재 땅에서의 식량 값을 result에 더함

                for(int i=0; i<4; i++) {
                    int nx = currX + dx[i];
                    int ny = currY + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] > 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }

            return result;
        }
    }
}

