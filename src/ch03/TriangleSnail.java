package ch03;

import java.util.Arrays;

/**
 * 삼각 달팽이 - Level2
 *
 * [문제]
 * 정수 n이 매개변수로 주어집니다. 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후,
 * 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해라
 *
 * [제한사항]
 * n은 1이상 1,000 이하입니다.
 * [입출력 예]
 * n=4, result=[1,2,9,3,10,8,4,5,6,7]
 * n=5, result=[1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
 * n=6, result=[1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
 */
public class TriangleSnail {
    // 문제 풀이 흐름
    // 1. n * n 2차원 배열 선언
    // 2. 숫자를 채울 현재 위치를 (0,0)으로 설정
    // 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기
    //  3-A. 아래로 이동하면서 숫자 채우기
    //  3-B. 오른쪽으로 이동하면서 숫자 채우기
    //  3-C. 왼쪽 위로 이동하면서 숫자 채우기
    // 4. 채워진 숫자를 차례대로 1차원 배열에 옮겨서 반환

    // 배열 index 가 의미하는 바
    // (0번째:아래로 이동한 경우 값의 변화량, 1번째:오른쪽으로 이동한 경우 값의 변화량, 2번째:왼쪽 위로 이동한 경우 값의 변화량)
    private static final int[] dx = {0, 1, -1};  // x의 변화량
    private static final int[] dy = {1, 0, -1};  // y의 변화량
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];   // 삼각형을 표현할 2차원 배열 선언
        int v = 1;  // 채워 넣을 숫자
        // 배열에 숫자를 넣을 위치를 변수로 선언 - (0,0) 위치부터 숫자를 넣는다
        int x = 0;
        int y = 0;
        int d = 0;  // 이동 방향 변수 (0=아래로, 1=오른쪽으로, 2=왼쪽 위로)

        while (true) {
            triangle[y][x] = v++;   // 좌표에 값 설정
            int nx = x + dx[d]; // 다음 이동할 x좌표
            int ny = y + dy[d]; // 다음 이동할 y좌표
            // 아래, 오른쪽, 왼쪽 위로 이동할 index 범위가 아니거나, 이동할 위치에 이미 값이 존재한 경우
            if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                d = (d + 1) % 3;    // 이동 방향 설정
                nx = x + dx[d]; // 다시 이동할 x좌표 설정
                ny = y + dy[d]; // 다시 이동할 y좌표 설정
                // 다시 이동할 좌표를 설정하였는데도, 또 이동할 수 없는 경우에는 반복문 종료
                if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {break;}
            }
            x = nx; // 이동이 가능하므로 x좌표 값을 변경
            y = ny; // 이동이 가능하므로 y좌표 값을 변경
        }

        int[] result = new int[v - 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = triangle[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TriangleSnail ts = new TriangleSnail();
        System.out.println(Arrays.toString(ts.solution(4)));
        System.out.println(Arrays.toString(ts.solution(5)));
        System.out.println(Arrays.toString(ts.solution(6)));
    }
}
