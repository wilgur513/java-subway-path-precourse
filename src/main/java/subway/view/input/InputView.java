package subway.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import subway.domain.StationRepository;

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

	private boolean validateOption(String option, String... validValues) {
		return Arrays.stream(validValues).anyMatch(value -> value.equals(option));
	}

	public List<String> inputStartAndEndStations() {
		String start = inputStartStation();
		String end = inputEndStation();

		if (start.equals(end)) {
			throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
		}

		return Arrays.asList(start, end);
	}

	private String inputEndStation() {
		String end = inputStation("도착역을 입력하세요.");
		if (!isExistStation(end)) {
			throw new IllegalArgumentException("[ERROR] 도착역이 존재하지 않습니다.");
		}
		return end;
	}

	private String inputStartStation() {
		String start = inputStation("출발역을 입력하세요.");
		if (!isExistStation(start)) {
			throw new IllegalArgumentException("[ERROR] 출발역이 존재하지 않습니다.");
		}
		return start;
	}

	private String inputStation(String message) {
		System.out.println("## " + message);
		String station = scanner.next();
		System.out.println();
		return station;
	}

	private boolean isExistStation(String name) {
		return StationRepository.existsByName(name);
	}
}
