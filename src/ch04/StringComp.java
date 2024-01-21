package ch04;

import java.util.Arrays;

/**
 * 문자열 압축 - Level 2
 *
 * [문제]
 * 데이터 처리 전문가가 되고 싶은 '어피치'는 문자열을 압축하는 방법에 대해 공부하고 있습니다.
 * 최근에 대량의 데이터 처리를 위한 간단한 비손실 압축 방법에 대해 공부하고 있는데, 문자열에서 같은 값이 연속해서 나타나는 것을
 * 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현하는 알고리즘을 공부하고 있습니다.
 *
 * 간단한 예로, "aabbaccc" 의 경우 "2a2ba3c"(문자가 반복되지 않아 한 번만 나타난 경우 1은 생략)와 같이 표현할 수 있는데,
 * 이러한 방식은 반복되는 무자가 적은 경우 압축률이 낮다는 단점이 있습니다.
 * 예를 들면, "abcabcdede" 와 같은 문자열은 전혀 압축이 되지 않습니다.
 * '어피치'는 이러한 단점을 해결하기 위해 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려고 합니다.
 *
 * 예를 들어 "ababcdcdababcdcd" 의 경우 문자를 1개 단위로 자르면 전혀 압축되지 않지만, 2개 단위로 잘라서 압축한다면
 * "2ab2cd2ab2cd" 로 압축 표현할 수 있습니다. 다른 방법으로 8개 단위로 잘라서 압축한다면 "2ababcdcd" 로 표현할 수 있으며
 * 이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다.
 *
 * 다른 예로, "abcabcdede" 와 같은 경우, 문자를 2개 단위로 잘라서 압축하면 "abcabc2de" 가 되지만, 3개 단위로 자른다면
 * "2abcdede" 가 되어 3개 단위가 가장 짧은 압축 방법이 됩니다.
 * 이때 3개 단위로 자르고 마지막에 남는 문자열은 그대로 붙여주면 됩니다.
 *
 * 압축할 문자열 s 가 매개변수로 주어질 때, 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중
 * 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한 사항]
 * s의 길이는 1 이상 1,000 이하입니다.
 * s는 알파벳 소문자로만 이루어져 있습니다.
 *
 * [입출력 예]
 * s = "aabbaccc"   result = 7
 * s = "ababcdcdababcdcd"   result = 9
 * s = "abcabcdede"     result = 8
 * s = "abcabcabcabcdededededede"   result = 14
 * s = "xababcdcdababcdcd"     result = 17
 */
public class StringComp {
    // 문제 해결 흐름
    // int minLength (압축된 문자열 중 제일 짧은 문자열 길이) 선언
    // 1. 1 부터 문자열의 길이/2 만큼 자를 문자열의 길이를 조정하면서 반복문 진행
    //    (문자열의 길이/2 보다 큰 값으로 자를 문자열의 길이를 지정하는건 압축률에 도움이 되지 않으므로)
    // 2. 설정된 문자열의 길이로 자른 문자열 배열 생성
    // 3. 문자열 배열의 문자열을 비교하여 하나의 문자열로 압축하고 해당 문자열의 길이가 가장 짧은지 비교
    // 4. 모든 반복문이 진행 후, 가장 짧은 문자열의 길이를 담은 변수 반환
    public int solution(String s) {
        int minLength = Integer.MAX_VALUE;
        // 원본 문자열 길이가 1이나 2인 경우 압축할 필요가 없으므로 그대로 문자열 길이 반환 처리
        if (s.length() == 1) {
            return s.length();
        }

        for (int i = 1; i <= s.length() / 2; i++) {
            // 자를 문자열 길이를 설정하고, 해당 길이만큼 잘라낸 문자열들로 만든 배열 생성
            String[] tmpArr = createCutArray(s, i);
            // 압축 문자열 생성
            StringBuilder sb = compressStringCreate(tmpArr);
            // 압축된 문자열의 길이가 가장 짧은 문자열인지 비교
            if (minLength > sb.length()) {
                minLength = sb.length();
            }
        }

        return minLength;
    }

    /**
     * 원본 문자열에서 설정된 길이로 잘라낸 문자열들로 만든 문자열 배열 생성
     * @param s : 원본 문자열
     * @param i : 자를 길이 설정
     * @return String[] : 설정된 길이로 잘라낸 문자열들로 만든 문자열 배열
     */
    private static String[] createCutArray(String s, int i) {
        int sLength = i;    // 자를 문자열 길이
        //  sLength 길이만큼 잘라낸 문자열들로 만든 배열의 길이 구하기
        int arrLength = s.length() % sLength == 0 ? s.length()/sLength : s.length()/sLength +1;
        String[] tmpArr = new String[arrLength]; // sLength 길이만큼 잘라낸 문자열들로 만든 배열 생성
        int startIdx = 0;   // 자를 문자열의 첫번째 index
        int lastIdx = startIdx+sLength; // 자를 문자열의 마지막 index
        // 설정된 문자열의 길이로 자른 문자열 배열 생성
        for (int j = 0; j < tmpArr.length; j++) {
            tmpArr[j] = s.substring(startIdx, lastIdx); // substring 의 lastIdx 는 포함되지 않음
            startIdx += sLength;    // 다음 자를 문자열 요소의 첫 index 값 조정
            lastIdx += sLength;     // 다음 자를 문자열 요소의 마지막 index 값 조정
            // 조정한 마지막 index 값이 문자열의 길이보다 크면 substring 진행 시, 예외 발생하므로 조정
            if(lastIdx > s.length()) {lastIdx = s.length();}
        }

        return tmpArr;
    }

    /**
     * 문자열 배열의 요소들을 압축한 문자열로 생성
     * @param tmpArr : 압축할 대상의 문자열을 담은 배열
     * @return StringBuilder : 압축된 문자열
     */
    private static StringBuilder compressStringCreate(String[] tmpArr) {
        StringBuilder sb = new StringBuilder();
        int sameStrCnt = 1;
        String currentStr = tmpArr[0];

        for (int k = 1; k < tmpArr.length; k++) {
            if (currentStr.equals(tmpArr[k])) {
                sameStrCnt++;
            } else {
                if (sameStrCnt > 1) {
                    sb.append(sameStrCnt);
                }
                sb.append(currentStr);
                currentStr = tmpArr[k];
                sameStrCnt = 1;
            }

            if (sameStrCnt == 1 && k == tmpArr.length - 1) {
                sb.append(currentStr);
            } else if (sameStrCnt > 1 && k == tmpArr.length - 1) {
                sb.append(sameStrCnt).append(currentStr);
            }
        }

        return sb;
    }

    public static void main(String[] args) {
        StringComp sc = new StringComp();
        String s = "aabbaccc";
        System.out.println(sc.solution(s));
        String s2 = "ababcdcdababcdcd";
        System.out.println(sc.solution(s2));
        String s3 = "abcabcdede";
        System.out.println(sc.solution(s3));
        String s4 = "abcabcabcabcdededededede";
        System.out.println(sc.solution(s4));
        String s5 = "xababcdcdababcdcd";
        System.out.println(sc.solution(s5));
        String s6 = "z";
        System.out.println(sc.solution(s6));

    }
}
