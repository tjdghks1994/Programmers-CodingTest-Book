package ch05;

import java.util.Arrays;

/**
 * 쿼드압축 후 개수 세기 - Level 2
 *
 * [문제]
 * 0과 1로 구성된 2n*2n 크기의 2차원 정수 배열 arr 이 있습니다.
 * 당신은 이 arr 을 쿼드 트리와 같은 방식으로 압축하고자 합니다.
 * 구체적인 방식은 다음과 같습니다.
 * 1. 당신이 압축하고자 하는 특정 영역을 S라고 정의합니다.
 * 2. 만약 S 내부에 있는 모든 수가 같은 값이라면, S를 해당 수 하나로 압축시킵니다.
 * 3. 그렇지 않다면 S를 정확히 네 개의 균일한 정사각형 영역으로 쪼갠 후 각 정사각형 영역에 대해 같은 방식의 압축을 시도합니다.
 * arr 이 매개변수로 주어집니다.
 * 이런 방식으로 arr 을 압축했을 때, 배열에 최종적으로 남는 0의 개수와 1의 개수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한 사항]
 * arr 의 행 개수는 1이상 1024 이하이며, 2의 거듭 제곱수 형태를 띱니다.
 * 즉, arr 의 행 개수는 1,2,4,8,16,...,1024 중 하나입니다.
 * arr 의 각 행 길이는 arr 의 행 개수와 같습니다. 즉. arr 은 정사각형 배열입니다.
 * arr 의 각 행에 있는 모든 값은 0 또는 1입니다.
 *
 * [입출력 예]
 * arr = [[1,1,0,0],[1,0,0,0],[1,0,0,1],[1,1,1,1]]      result = [4,9]
 * arr = [[1,1,1,1,1,1,1,1],[0,1,1,1,1,1,1,1],[0,0,0,0,1,1,1,1],[0,1,0,0,1,1,1,1]
 *          ,[0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,1],[0,0,0,0,1,0,0,1],[0,0,0,0,1,1,1,1]]       result = [10,15]
 */
public class QuadCompressionCounting {

    private static class Count {
        public final int zero;
        public final int one;

        public Count(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }

        public Count add(Count other) {
            return new Count(zero + other.zero, one + other.one);
        }
    }

    private Count count(int offsetX, int offsetY, int size, int[][] arr) {
        int h = size / 2;
        for (int x = offsetX; x < offsetX + size; x++) {
            for (int y = offsetY; y < offsetY + size; y++) {
                if (arr[y][x] != arr[offsetY][offsetX]) {
                    return count(offsetX, offsetY, h, arr)
                            .add(count(offsetX + h, offsetY, h, arr))
                            .add(count(offsetX, offsetY + h, h, arr))
                            .add(count(offsetX + h, offsetY + h, h, arr));
                }
            }
        }

        if (arr[offsetY][offsetX] == 1) {
            return new Count(0, 1);
        }
        return new Count(1, 0);
    }

    public int[] solution(int[][] arr) {
        Count count = count(0, 0, arr.length, arr);
        return new int[]{count.zero, count.one};
    }

    public static void main(String[] args) {
        QuadCompressionCounting qcc = new QuadCompressionCounting();
        System.out.println(Arrays.toString(qcc.solution(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}})));
    }
}
