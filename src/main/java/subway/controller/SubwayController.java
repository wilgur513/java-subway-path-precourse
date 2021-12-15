package subway.controller;

import subway.domain.Subway;
import subway.view.input.InputView;
import subway.view.output.PrintPageView;

public class SubwayController {
	private static final InputView input = new InputView();
	private final Subway subway;

	public SubwayController() {
		this.subway = createSubway();
	}

	private Subway createSubway() {
		Subway subway = new Subway();
		subway.addObserver(new PrintPageView());
		return subway;
	}

	public void service() {
		subway.mainPage();
		String option = input.inputOption();

		if (option.equals("1")) {
			handleSelectCoursePage();
		}
	}

	public void handleSelectCoursePage() {
		subway.selectPage();
		String option = input.inputOption();

		if (option.equals("1")) {
			String start = input.inputStation("출발역을 입력하세요.");
			String end = input.inputStation("도착역을 입력하세요.");
			subway.shortDistancePath(start, end);
		} else if(option.equals("2")) {
			String start = input.inputStation("출발역을 입력하세요.");
			String end = input.inputStation("도착역을 입력하세요.");
			subway.shortTimePath(start, end);
		}
	}
}
