package ch04;

import java.util.Arrays;

/**
 * 이진 변환 반복하기 - Level 2
 *
 * [문제]
 * 0과 1로 이루어진 어떤 문자열 x에 대한 이진 변환을 다음과 같이 정의합니다.
 * - x의 모든 0을 제거합니다.
 * - x의 길이를 c라고 하면, x를 'c를 2진법으로 표현한 문자열'로 바꿉니다.
 * 예를 들어 x="0111010" 이라면, x에 이진변환을 가하면 x="0111010" -> "1111" -> "100" 이 됩니다.
 * 0과 1로 이루어진 문자열 s가 매개변수로 주어집니다.
 * s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때,
 * 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 각각 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한 사항]
 * s의 길이는 1 이상 150,000 이하입니다.
 * s에는 '1'이 최소 하나 이상 포함되어 있습니다.
 *
 * [입출력 예]
 * s = "110010101001"   result = [3,8]
 * s = "01110"  result = [3,3]
 * s = "1111111"    result = [4,1]
 */
public class BinaryConversionIteration {
    // 문제 해결 흐름
    // 1. 문자열 s에서 0을 모두 제거 ( replaceAll() 활용)
    // 1-a. 제거된 0의 개수를 removeZeroCount 변수에 저장
    // 2. 제거한 문자열의 길이를 구하기
    // 3. 해당 길이를 이진 문자열로 변환하기 ( Integer.toString(int, radix) 활용 )
    // 3-a. 이진 변환 횟수를 나타내는 conversionCount 변수 값을 1 증가
    // 4. 1~3의 과정을 문자열 s가 "1"이 될 때까지 재귀 호출
    public int[] solution(String s) {
        int removeZeroCount = 0;
        int conversionCount = 0;
        int[] answer = new int[]{conversionCount, removeZeroCount};

        if (!s.equals("1")) {
            solution(s, answer);
        }

        return answer;
    }

    private void solution(String s, int[] answer) {
        int originLength = s.length();
        s = s.replaceAll("0", "");
        int replaceLength = s.length();
        answer[1] += originLength - s.length(); // 제거된 0 개수
        String conversionBinaryString = Integer.toString(replaceLength, 2); // 이진 문자열로 변환
        answer[0]++;
        // 문자열이 1이 될 때까지 재귀 호출
        if (!conversionBinaryString.equals("1")) {
            solution(conversionBinaryString, answer);
        }
    }

    public static void main(String[] args) {
        BinaryConversionIteration bci = new BinaryConversionIteration();
        System.out.println(Arrays.toString(bci.solution("110010101001")));
        System.out.println(Arrays.toString(bci.solution("01110")));
        System.out.println(Arrays.toString(bci.solution("1111111")));
        System.out.println(Arrays.toString(bci.solution("1")));
    }
}
