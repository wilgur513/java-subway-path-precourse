package subway;

import java.util.Scanner;
import subway.domain.Subway;
import subway.view.output.PrintPageView;

public class Application {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Subway subway = initSubway();
		service(subway);
	}

	private static Subway initSubway() {
		Subway subway = new Subway();
		subway.addObserver(new PrintPageView());
		return subway;
	}

	private static void service(Subway subway) {
		subway.mainPage();
		String option = inputOption();

		if (option.equals("1")) {
			handleSelectCoursePage(subway);
		}
	}

	private static String inputOption() {
		System.out.println("## 원하는 기능을 선택하세요.");
		String option = scanner.next();
		System.out.println();
		return option;
	}

	private static void handleSelectCoursePage(Subway subway) {
		subway.selectPage();
		String option = inputOption();

		if (option.equals("1")) {
			String start = inputStation("출발역을 입력하세요.");
			String end = inputStation("도착역을 입력하세요.");
            subway.shortDistancePath(start, end);
		}
	}

	private static String inputStation(String message) {
		System.out.println("## " + message);
		String station = scanner.next();
		System.out.println();
		return station;
	}
}
