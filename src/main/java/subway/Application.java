package subway;

import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMainPage();
        printSelectCourse(inputOption());
    }

    private static void printMainPage() {
        System.out.println("## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
    }

    private static String inputOption() {
        System.out.println("## 원하는 기능을 선택하세요.");
        String option = scanner.next();
        return option;
    }

    private static void printSelectCourse(String option) {
        if (option.equals("1")) {
            System.out.println("## 경로 기준");
            System.out.println("1. 최단 거리");
            System.out.println("2. 최소 시간");
            System.out.println("B. 돌아가기");
        }
    }
}
