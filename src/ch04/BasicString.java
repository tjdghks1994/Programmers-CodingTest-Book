package ch04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열 다루기 기본 - Level 1
 *
 * [문제]
 * 문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼 있는지 확인해주는 함수, solution 을 완성하세요.
 * 예를 들어 s가 "a234" 이면 False 를 리턴하고 "1234" 라면 True 를 리턴하면 됩니다.
 *
 * [제한 사항]
 * s는 길이 1 이상, 8 이하인 문자열입니다.
 * s는 영문 알파벳 대소문자 또는 0부터 9까지 숫자로 이루어져 있습니다.
 *
 * [입출력 예]
 * s = "a234"   result = false
 * s = "1234"   result =true
 */
public class BasicString {

    // 문제 풀이 흐름
    // 1. s의 길이가 4 혹은 6이면서, 숫자로만 구성돼 있는지 판단을 정규표현식을 활용
    // 2. String 클래스의 matches() 메소드 활용
    public boolean solution(String s) {
        return s.matches("[0-9]{4}|[0-9]{6}");
    }

    public static void main(String[] args) {
        BasicString bs = new BasicString();
        System.out.println(bs.solution("a234"));
        System.out.println(bs.solution("1234"));
    }
}
