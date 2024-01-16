package ch04;

/**
 * 시저 암호 - Level 1
 *
 * [문제]
 * 어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
 * 예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다. "z"는 1만큼 밀면 "a"가 됩니다.
 * 문자열 s와 거리 n을 입력받아 s를 n 만큼 민 암호문을 만드는 solution 함수를 완성해보세요
 *
 * [제한 조건]
 * 공백은 아무리 밀어도 공백입니다
 * 문자열 s는 알파벳 소문자, 대문자, 공백으로만 구성되어 있습니다
 * s의 길이는 8000 이하입니다.
 * n은 1이상, 25 이하인 자연수입니다.
 *
 * [입출력 예]
 * s = "AB"   n = 1   result = "BC"
 * s = "z"    n = 1   result = "a"
 * s = "a B z"  n = 4  result = "e F d"
 */
public class CaesarCipher {
    // 문제 풀이 흐름
    // 1. 문자열을 문자배열로 변환
    // 2. 문자배열 각 요소에 n의 값을 더한다
    //   2-A. 만약 문자배열 값이 32인 경우 공백이므로 다음 문자배열로 이동한다
    //   2-B. 문자배열+n의 값이 소문자의 범위를 초과할 수 있으므로, 문자배열+n-'a' 의 값이 25를 초과하는지 체크한다
    //        초과한다면 초과한 값을 'a'와 더한 후 -1을 한 값으로 배열에 저장한다
    //        초과하지 않는다면 문자배열+n의 값으로 배열에 저장한다
    //   2-C. 문자배열+n의 값이 대문자의 범위를 초과할 수 있으므로, 문자배열+n-A'의 값이 25를 초과하는지 체크한다
    //        초과한다면 초과한 값을 'A'와 더한 후 -1을 한 값으로 배열에 저장한다
    //        초과하지 않는다면 문자배열+n의 값으로 배열에 저장한다
    // 3. 문자배열을 문자열로 변환하여 반환한다

    public String solution(String s, int n) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == 32) {
                continue;
            }

            if ('A' <= chars[i] && chars[i] <= 'Z') {
                int cSum = chars[i] + n - 'A';
                if (cSum > 25) {
                    chars[i] = (char) (cSum - 25 + 'A' - 1);
                } else {
                    chars[i] = (char) (chars[i]+n);
                }
            } else if ('a' <= chars[i] && chars[i] <= 'z') {
                int cSum = chars[i] + n - 'a';
                if (cSum > 25) {
                    chars[i] = (char) (cSum - 25 + 'a' - 1);
                } else {
                    chars[i] = (char) (chars[i]+n);
                }
            }
        }

        String answer = new String(chars);

        return answer;
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.solution("AB", 1));
        System.out.println(cc.solution("z", 1));
        System.out.println(cc.solution("a B z", 4));

    }

}
