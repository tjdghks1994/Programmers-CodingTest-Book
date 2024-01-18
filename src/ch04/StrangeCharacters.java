package ch04;

/**
 * 이상한 문자 만들기 - Level 1
 *
 * [문제]
 * 문자열 s는 한 개 이상의 단어로 구성되어 있습니다.
 * 각 단어는 하나 이상의 공백 문자로 구분되어 있습니다.
 * 각 단어의 짝수 번째 알파벳은 대문자로, 홀수 번째 알파벳은 소문자로 바꾼 문자열을 return 하는 함수,
 * solution 을 완성하세요.
 *
 * [제한 사항]
 * 문자열 전체의 짝/홀수 인덱스가 아니라, 단어(공백을 기준)별로 짝/홀수 인덱스를 판단해야 합니다.
 * 첫 번째 글자는 0번째 인덱스로 보아 짝수 번째 알파벳으로 처리해야 합니다.
 *
 * [입출력 예]
 * s = "try hello world"    result = "TrY HeLlO WoRlD"
 */
public class StrangeCharacters {
    // 문제 풀이 흐름
    // 1. 문자열을 문자 배열로 변환, StringBuilder 변수 선언, 현재 단어의 인덱스를 나타내기 위한 변수 선언
    // 2. 문자 배열만큼 반복문 진행
    //  2-A. 만약 단어가 공백이라면 위의 인덱스 변수를 0으로 초기화하고 StringBuilder 에 append
    //       반복문 continue (공백인지 아닌지 판단은 아스키 코드를 활용)
    //  2-B. 현재 단어의 인덱스가 홀수인지 판단하고 소문자로 변환 후, StringBuilder 에 append
    //  2-C. 현재 단어의 인덱스가 짝수인지 판단하고 대문자로 변환 후, StringBuilder 에 append

    public String solution(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int charIdx = 0;

        for (int i = 0; i < c.length; i++) {
            if (32 == c[i]) {
                charIdx = 0;
                sb.append(c[i]);
                continue;
            }

            boolean odd = charIdx % 2 == 1;
            if (odd) {  // 홀수
                sb.append(Character.toLowerCase(c[i]));
            } else {    // 짝수
                sb.append(Character.toUpperCase(c[i]));
            }
            charIdx++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StrangeCharacters sc = new StrangeCharacters();
        String s = "try hello world";
        System.out.println(sc.solution(s));
        String s2 = "ttt  tttttt dkdkdk";
        System.out.println(sc.solution(s2));
    }
}
