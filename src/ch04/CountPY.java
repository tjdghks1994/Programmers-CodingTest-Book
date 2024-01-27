package ch04;

/**
 * 문자열 내 p와 y의 개수 - Level 1
 *
 * [문제]
 * 대문자와 소문자가 섞여 있는 문자열 s가 주어집니다.
 * s에 'p'의 개수와 'y'의 개수를 비교하여 같으면 true, 다르면 false 를 return 하는 solution 함수를 완성해주세요.
 * 'p','y' 모두 하나도 없을 때는 항상 true 를 return 합니다.
 * 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다.
 * 예를 들어 s가 "pPoooyY"면 true 를 return 하고, "PYy" 라면 false 를 return 합니다.
 *
 * [제한 사항]
 * 문자열 s의 길이는 50 이하의 자연수
 * 문자열 s는 알파벳으로만 구성되어 있습니다
 *
 * [입출력 예]
 * s = "pPoooyY"    result = true
 * s = "PYy"        result = false
 */
public class CountPY {
    // 문제 해결 흐름
    // 1. p와 y의 개수가 같은지 판단할 int 변수 선언 (초기값은 0)
    //    2개의 포인터 변수 선언 ( left = 0, right = s.length-1 )
    //    문자열 s의 값을 모두 소문자로 변환
    // 2. while 반복문 활용 (종료 조건은 left 와 right 가 교차하였을 때)
    // 2-a.  if 문으로 p, y의 아스키 코드 값을 활용해 체크 (p=112, y=121)
    // 2-b.  p 값이면 1에 선언한 변수의 값을 -1, y 값이면 1에 선언한 변수의 값을 +1
    //       단, left 와 right 값이 동일한 경우에는 실행이 2번되지 않도록 해야함
    // 2-c.  left++, right--
    // 3. 반복문이 끝난뒤, 1에 선언한 변수의 값이 0이라면, 개수가 같으므로 true 반환
    //    1에 선언한 변수의 값이 0이아니라면, 개수가 다르므로 false 반환
    public boolean solution(String s) {
        int count = 0;
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();

        while (left <= right) {
            if (left != right) {
                count = checkRightPointer(s, right, count);
            }
            count = checkLeftPointer(s, left, count);
            left++;
            right--;
        }
        return count == 0;
    }

    private static int checkRightPointer(String s, int right, int count) {
        if (s.charAt(right) == 112) {
            count--;
        }
        if (s.charAt(right) == 121) {
            count++;
        }
        return count;
    }

    private static int checkLeftPointer(String s, int left, int count) {
        if (s.charAt(left) == 112) {
            count--;
        }
        if (s.charAt(left) == 121) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        CountPY cpy = new CountPY();
        System.out.println(cpy.solution("pPoooyY"));
        System.out.println(cpy.solution("PYy"));
    }
}
