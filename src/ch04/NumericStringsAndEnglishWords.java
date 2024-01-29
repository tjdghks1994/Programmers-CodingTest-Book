package ch04;

import java.util.HashMap;
import java.util.Map;

/**
 * 숫자 문자열과 영단어 - Level 1
 *
 * [문제]
 * '네오'와 '프로도'가 숫자놀이를 하고 있습니다.
 * '네오'가 '프로도'에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네 주면 프로도는 원래 숫자를 찾는 게임입니다.
 * 다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.
 * 1478 -> "one4seveneight"
 * 234567 -> "23four5six7"
 * 10203 -> "1zerotwozero3"
 * 이렇게 숫자의 일부 자릿수가 영단어로 바뀌었거나, 혹은 바뀌지 않고 그대로인 문자열 s가 매개변수로 주어집니다.
 * s가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한 사항]
 * 1 <= s의 길이 <= 50
 * s가 "zero" 또는 "0" 으로 시작하는 경우는 주어지지 않습니다.
 * return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 s로 주어집니다.
 *
 * [입출력 예]
 * s = "one4seveneight"     result = 1478
 * s = "23four5six7"        result = 234567
 * s = "2three45sixseven"   result = 234567
 * s = "123"                result = 123
 */
public class NumericStringsAndEnglishWords {
    // 문제 해결 흐름
    // 0~9까지 key = 영단어, value = 숫자를 담고 있는 HashMap 선언
    // StringBuilder tmp 선언, StringBuilder result 선언, 문자열 s를 char(문자) 배열로 변환
    // 1. 문자 배열의 길이만큼 반복문
    // 1-a. 문자 배열의 요소가 숫자라면
    //         result.append(문자 배열 요소);
    // 1-b. 문자 배열의 요소가 숫자가 아니라면
    //          tmp.append(문자 배열 요소);
    //          HashMap.containsKey(tmp.toString()) 이 true 이면
    //              result.append(HashMap.get(tmp.toString()));
    //              tmp 초기화
    // 2. 반복문 종료 후, result.toString() 값을 숫자로 변환 후 반환
    private int solution(String s) {
        HashMap<String, Integer> hash = new HashMap<>();
        initHashMap(hash);
        StringBuilder tmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        char[] cArray = s.toCharArray();

        for (int i = 0; i < cArray.length; i++) {
            // 아스키 코드 값을 통해 숫자인지 체크
            if (cArray[i] >= 48 && cArray[i] <= 57) {
                result.append(cArray[i]);
            } else {
                tmp.append(cArray[i]);

                if (hash.containsKey(tmp.toString())) {
                    result.append(hash.get(tmp.toString()));
                    tmp.setLength(0);
                }
            }
        }

        return Integer.valueOf(result.toString());
    }

    public static void main(String[] args) {
        NumericStringsAndEnglishWords nsaew = new NumericStringsAndEnglishWords();
        System.out.println(nsaew.solution("one4seveneight"));
        System.out.println(nsaew.solution("23four5six7"));
        System.out.println(nsaew.solution("2three45sixseven"));
        System.out.println(nsaew.solution("123"));
    }

    private void initHashMap(Map<String, Integer> hashMap) {
        hashMap.put("zero", 0);
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);
    }
}
