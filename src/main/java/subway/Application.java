package subway;

import java.util.Scanner;
import subway.domain.Subway;
import subway.view.output.PrintPageView;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Subway subway = new Subway();
        subway.addObserver(new PrintPageView());

        subway.mainPage();
        String option = inputOption();

        if(option.equals("1")) {
            subway.selectPage();
        }
    }

    private static String inputOption() {
        System.out.println("## 원하는 기능을 선택하세요.");
        String option = scanner.next();
        System.out.println();
        return option;
    }
}
