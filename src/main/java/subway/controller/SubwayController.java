package subway.controller;

import java.util.Arrays;
import java.util.List;
import subway.domain.Subway;
import subway.view.input.InputView;
import subway.view.output.PrintPageView;
import subway.view.output.PrintPathResultView;

public class SubwayController {
	private static final InputView input = new InputView();
	private final Subway subway;

	public SubwayController() {
		this.subway = createSubway();
	}

	private Subway createSubway() {
		Subway subway = new Subway();
		subway.addObserver(new PrintPageView());
		subway.addObserver(new PrintPathResultView());
		return subway;
	}

	public void service() {
		while (true) {
			subway.mainPage();
			String option = inputOption("1", "Q");

			if (option.equals("1")) {
				handleSelectCoursePage();
			} else if (option.equals("Q")) {
				break;
			}
		}
	}

	private String inputOption(String... validValues) {
		try {
			return input.inputOption(validValues);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println();
			return inputOption(validValues);
		}
	}

	public void handleSelectCoursePage() {
		subway.selectPage();
		String option = inputOption("1", "2", "B");
		handleOptionValue(option);
	}

	private void handleOptionValue(String option) {
		if (option.equals("B")) {
			return;
		}

		List<String> stations = inputStartAndEndStations();

		if (option.equals("1")) {
			subway.shortDistancePath(stations.get(0), stations.get(1));
		} else if (option.equals("2")) {
			subway.shortTimePath(stations.get(0), stations.get(1));
		}
	}

	private List<String> inputStartAndEndStations() {
		String start = input.inputStation("출발역을 입력하세요.");
		String end = input.inputStation("도착역을 입력하세요.");
		return Arrays.asList(start, end);
	}
}
