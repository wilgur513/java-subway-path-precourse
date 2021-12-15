package subway.controller;

import java.util.List;
import subway.domain.SubwayMap;
import subway.view.input.InputView;
import subway.view.output.OutputView;

public class SubwayController {
	private static final InputView input = new InputView();

	public void service() {
		String option = handleMainPage();
		while (!option.equals("Q")) {
			handleSelectPathPage();
			option = handleMainPage();
		}
	}

	private String handleMainPage() {
		OutputView.printMainPage();
		return inputOption("1", "Q");
	}

	private String inputOption(String... validValues) {
		try {
			return input.inputOption(validValues);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputOption(validValues);
		}
	}

	public void handleSelectPathPage() {
		OutputView.printSelectPath();
		handleSelectPath(inputOption("1", "2", "B"));
	}

	private void handleSelectPath(String option) {
		if (isBackward(option)) {
			return;
		}
		findShortestPath(option);
	}

	private boolean isBackward(String option) {
		return option.equals("B");
	}

	private void findShortestPath(String option) {
		List<String> stations = inputStartAndEndStations();
		try {
			findShortestPathBetweenStations(option, stations.get(0), stations.get(1));
		} catch (Exception e) {
			OutputView.printErrorMessage("출발역과 도착역이 연결되어 있지 않습니다.");
			handleSelectPath(option);
		}
	}

	private List<String> inputStartAndEndStations() {
		try {
			return input.inputStartAndEndStations();
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputStartAndEndStations();
		}
	}

	private void findShortestPathBetweenStations(String option, String start, String end) {
		OutputView.printPathResult(
			SubwayMap
				.createSubwayMap(option)
				.getPathResult(start, end));
	}
}
