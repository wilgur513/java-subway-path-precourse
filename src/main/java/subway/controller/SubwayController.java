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
			String option = input.inputOption();

			if (!option.equals("1") && !option.equals("Q")) {
				printErrorMessage();
			}

			if (option.equals("1")) {
				handleSelectCoursePage();
			} else if (option.equals("Q")) {
				break;
			}
		}
	}

	private void printErrorMessage() {
		System.out.println("[ERROR] 잘못된 기능입니다.");
		System.out.println();
	}

	public void handleSelectCoursePage() {
		subway.selectPage();
		String option = input.inputOption();
		validateOptionValue(option);
		handleOptionValue(option);
	}

	private void validateOptionValue(String option) {
		if(!option.equals("1") && !option.equals("2") && !option.equals("B")) {
			printErrorMessage();
			handleSelectCoursePage();
		}
	}

	private void handleOptionValue(String option) {
		if (option.equals("1")) {
			List<String> stations = inputStartAndEndStations();
			subway.shortDistancePath(stations.get(0), stations.get(1));
		} else if (option.equals("2")) {
			List<String> stations = inputStartAndEndStations();
			subway.shortTimePath(stations.get(0), stations.get(1));
		}
	}

	private List<String> inputStartAndEndStations() {
		String start = input.inputStation("출발역을 입력하세요.");
		String end = input.inputStation("도착역을 입력하세요.");
		return Arrays.asList(start, end);
	}
}
