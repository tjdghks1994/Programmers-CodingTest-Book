package ch03;

import java.util.Arrays;

/**
 * 행렬의 곱셈 - Level2
 *
 * [문제]
 * 2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환하는 함수, solution을 완성해주세요
 *
 * [제한조건]
 * 행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
 * 행렬 arr1, arr2의 원소는 -10 이상 20 이하인 자연수입니다.
 * 곱할 수 있는 배열만 주어집니다.
 *
 * [입출력 예]
 * arr1 = [[1,4],[3,2],[4,1]]  arr2 = [[3,3],[3,3]] return = [[15,15],[15,15],[15,15]]
 * arr1 = [[2,3,2],[4,2,4],[3,1,4]]  arr2 = [[5,4,3],[2,4,1],[3,1,1]]  return = [[22,22,11],[36,28,18],[29,20,14]]
 */
public class ArrayMultiple {
    // 행렬의 곱셈은 또 다른 행렬이 생성된다
    // 3행 2열의 행렬과 2행 2열의 행렬을 곱하면 3행 2열의 행렬이 만들어진다
    // 즉, 첫 번째 행렬의 행과 두 번째 행렬의 열이 새로운 행렬의 행과 열이 되는 규칙이 있다

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < arr1[i].length; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int arr1[][] = new int[][]{{1, 4}, {3, 2}, {4, 1}};
        int arr2[][] = new int[][]{{3, 3}, {3, 3}};

        int arr3[][] = new int[][]{{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int arr4[][] = new int[][]{{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};

        System.out.println("arr1 = " + Arrays.deepToString(arr1) + ", arr2 = " + Arrays.deepToString(arr2));
        System.out.println("arr1 * arr2 = " + Arrays.deepToString(solution(arr1, arr2)));
        System.out.println("arr3 = " + Arrays.deepToString(arr3) + ", arr4 = " + Arrays.deepToString(arr4));
        System.out.println("arr3 * arr4 = " + Arrays.deepToString(solution(arr3, arr4)));
    }
}
