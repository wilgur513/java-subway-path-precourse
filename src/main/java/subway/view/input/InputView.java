package subway.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public String inputOption(String... validValues) {
		System.out.println("## 원하는 기능을 선택하세요.");
		String option = scanner.next();
		System.out.println();

		if (!validateOption(option, validValues)) {
			throw new IllegalArgumentException("[ERROR] 잘못된 기능입니다.");
		}

		return option;
	}

	public List<String> inputStartAndEndStations() {
		String start = inputStation("출발역을 입력하세요.");
		String end = inputStation("도착역을 입력하세요.");

		if(start.equals(end)) {
			throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
		}

		return Arrays.asList(start, end);
	}

	private String inputStation(String message) {
		System.out.println("## " + message);
		String station = scanner.next();
		System.out.println();
		return station;
	}

	private boolean validateOption(String option, String... validValues) {
		return Arrays.stream(validValues).anyMatch(value -> value.equals(option));
	}
}
