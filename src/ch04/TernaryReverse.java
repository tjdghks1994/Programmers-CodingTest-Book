package ch04;

import java.util.Stack;

/**
 * 3진법 뒤집기 - Level 1
 *
 * [문제]
 * 자연수 n 이 매개변수로 주어집니다.
 * n 을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한 사항]
 * n은 1 이상 100,000,000 이하인 자연수입니다.
 *
 * [입출력 예]
 * n = 45,  result = 7
 * n = 125, result = 229
 */
public class TernaryReverse {

    // 문제 풀이 흐름
    // 1. n을 3진법으로 변경한다
    //  1-A. 3진법으로 변경하기 위해서는 n이 3보다 작아질 때 까지, 계속 3으로 나누고
    //       나눈 나머지 값을 스택에 넣는다
    //  1-B. n이 3보다 작아진 경우, 해당 값을 스택에 넣는다
    //  1-C. 스택에 넣어둔 값을 차례대로 꺼내면 3진법이다 (StringBuilder 로 3진법 문자열을 담는다)
    // 2. 3진법의 수를 앞뒤 반전한다
    //  2-A. StringBuilder 가 제공하는 reverse() 메소드를 활용해 3진법 문자열을 반전한다
    // 3. 반전된 수를 다시 10진법으로 변경한다
    //  3-A. 반전된 3진법 문자열을 Integer.parseInt(String, radix) 메소드를 활용해 10진법으로 변경한다
    // 4. 변경된 수를 반환한다
    public int solution(int n) {
        Stack<String> ternaryReverseStack = new Stack<>();
        while (n >= 3) {
            int mod = n % 3;    // 나머지
            n /= 3; // 몫
            ternaryReverseStack.push(String.valueOf(mod));  // 나머지를 스택에 push
        }
        ternaryReverseStack.push(String.valueOf(n));    // while 문이 끝난 뒤, n의 값을 스택에 push

        StringBuilder sb = new StringBuilder();
        while (!ternaryReverseStack.isEmpty()) {
            sb.append(ternaryReverseStack.pop());
        }

        // 위에 스택을 통해 구현한 소스를 Integer.toString(int, radix) 메소드를 활용하면 아주 쉽게 정수를 radix 진법의 문자열로 변경 가능하다
        // StringBuilder sb = new StringBuilder(Integer.toString(n, 3));

        String ternaryReverseString = sb.reverse().toString();  // 반전된 수
        int ternaryReverse = Integer.parseInt(ternaryReverseString, 3);

        return ternaryReverse;
    }

    public static void main(String[] args) {
        TernaryReverse tr = new TernaryReverse();
        System.out.println(tr.solution(45));
        System.out.println(tr.solution(125));
    }
}
