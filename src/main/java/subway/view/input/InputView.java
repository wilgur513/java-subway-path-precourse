package subway.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import subway.domain.StationRepository;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public String inputOption(String... validValues) {
		String option = inputWithMessage("원하는 기능을 선택하세요.");

		if (!validateOption(option, validValues)) {
			throw new IllegalArgumentException("잘못된 기능입니다.");
		}

		return option;
	}

	private boolean validateOption(String option, String... validValues) {
		return Arrays.stream(validValues).anyMatch(value -> value.equals(option));
	}

	public List<String> inputStartAndEndStations() {
		String start = inputStation("출발");
		String end = inputStation("도착");

		if (start.equals(end)) {
			throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
		}

		return Arrays.asList(start, end);
	}

	private String inputStation(String type) {
		String station = inputWithMessage(type + "역을 입력하세요.");
		if (!isExistStation(station)) {
			throw new IllegalArgumentException(type + "역이 존재하지 않습니다.");
		}
		return station;
	}

	private String inputWithMessage(String message) {
		System.out.println("## " + message);
		String station = scanner.next();
		System.out.println();
		return station;
	}

	private boolean isExistStation(String name) {
		return StationRepository.existsByName(name);
	}
}
