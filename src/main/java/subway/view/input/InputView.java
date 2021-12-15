package subway.view.input;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public String inputOption() {
		System.out.println("## 원하는 기능을 선택하세요.");
		String option = scanner.next();
		System.out.println();
		return option;
	}

	public String inputStation(String message) {
		System.out.println("## " + message);
		String station = scanner.next();
		System.out.println();
		return station;
	}
}
