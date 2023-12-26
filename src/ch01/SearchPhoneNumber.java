package ch01;

import java.util.*;

public class SearchPhoneNumber {

    private static class PhoneNumber {
        public final String phoneNumber;
        // 전화번호는 숫자를 제외한 모든 문자열을 제거하고 표현
        public PhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        }

        @Override
        public String toString() {
            return "PhoneNumber{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneNumber that = (PhoneNumber) o;
            return Objects.equals(getPhoneNumber(), that.getPhoneNumber());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPhoneNumber());
        }
    }

    private static class Person {
        private final String name;  // 사용자 명
        private final List<PhoneNumber> phoneNumbers;   // 폰번호 목록

        public Person(String name) {
            this.name = name;
            this.phoneNumbers = new ArrayList<>();
        }
        // 사용자의 폰번호를 추가하는 역할의 메서드
        // 만약, 매개변수가 PhoneNumber 가 아니라 String 문자열로 받도록 구현된 메서드였다면
        // 아래 메서드에서 폰번호의 형식까지 검사하도록 구현해야되므로 여러 역할을 담당하게 된다!
        // 하지만 PhoneNumber 라는 클래스를 만들어 PhoneNumber 객체를 생성할 때, 폰번호의 형식을 통일하도록 구현하였기 때문에
        // 아래 메서드에서는 폰번호를 추가하는 역할만 할 수 있게 된 것이다!!!!
        public void addPhoneNumber(PhoneNumber phoneNumber) {
            phoneNumbers.add(phoneNumber);
        }
        // 사용자가 전달된 폰번호를 가지고 있는지 검사하는 역할의 메서드
        public boolean hasPhoneNumber(PhoneNumber phoneNumber) {
            return phoneNumbers.contains(phoneNumber);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", phoneNumbers=" + phoneNumbers +
                    '}';
        }
    }

    private static class PhoneBook {
        private final Set<Person> people;

        public PhoneBook() {
            people = new HashSet<>();
        }

        public void addPerson(Person person) {
            people.add(person);
        }
        //  전화번호부에 등록된 사람들 중 전달받은 폰번호를 가지고 있는 사람을 검색하는 역할의 메서드
        public Person search(PhoneNumber phoneNumber) {
            return people.stream().filter(p -> p.hasPhoneNumber(phoneNumber)).findFirst().orElse(null);
        }

        @Override
        public String toString() {
            return "PhoneBook{" +
                    "people=" + people +
                    '}';
        }
    }



    public static void main(String[] args) {
        Person person = new Person("홍길동");
        person.addPhoneNumber(new PhoneNumber("010-1234-5678"));
        person.addPhoneNumber(new PhoneNumber("010 1234 5678"));
        person.addPhoneNumber(new PhoneNumber("01012345678"));
        System.out.println(person);
        System.out.println(person.hasPhoneNumber(new PhoneNumber("01012345678")));

        Person person1 = new Person("홍길동");
        person1.addPhoneNumber(new PhoneNumber("010-1234-5678"));
        person1.addPhoneNumber(new PhoneNumber("010-2345-6789"));

        Person person2 = new Person("김철수");
        person2.addPhoneNumber(new PhoneNumber("010-2468-0246"));

        Person person3 = new Person("이영희");
        person3.addPhoneNumber(new PhoneNumber("010-1357-9135"));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson(person1);
        phoneBook.addPerson(person2);
        phoneBook.addPerson(person3);

        System.out.println(phoneBook);

        System.out.println(phoneBook.search(new PhoneNumber("01023456789")));
        System.out.println(phoneBook.search(new PhoneNumber("01024680246")));
        System.out.println(phoneBook.search(new PhoneNumber("01013579135")));
        System.out.println(phoneBook.search(new PhoneNumber("01000000000")));

    }


}
