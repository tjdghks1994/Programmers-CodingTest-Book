package ch04;

import java.util.Arrays;

/**
 * 자연수 뒤집어 배열로 만들기 - Level 1
 *
 * [문제]
 * 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요.
 * 예를 들어 n이 12345면 [54321]을 리턴합니다.
 *
 * [제한 조건]
 * n은 10,000,000,000 이하인 자연수입니다.
 *
 * 입출력 예
 * n = 12345  return = [5,4,3,2,1]
 */
public class TurnNumberIntoArray {
    public int[] solution(int n) {
        // 1. 문자열로 변환한다
        // 2. 문자열을 문자 배열로 변환한 후, 뒤집는다
        // 3. 문자 배열의 각문자를 정수로 변환하여 배열에 담아 반환한다
        String numStr = String.valueOf(n);
        int left = 0;
        int right = numStr.length() - 1;
        char[] c = numStr.toCharArray();
        while (left < right) {
            char tmp = c[left];
            c[left++] = c[right];
            c[right--] = tmp;
        }
        int[] result = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            result[i] = c[i] - '0'; // 숫자를 표현하는 문자는 자신의 아스키 코드에서 '0'의 아스키 코드 값을 빼면 자신의 정수 값이 나오게 된다
        }

        return result;
    }

    public static void main(String[] args) {
        TurnNumberIntoArray t = new TurnNumberIntoArray();
        System.out.println(Arrays.toString(t.solution(12345)));
    }
}
