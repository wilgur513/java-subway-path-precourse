package subway.controller;

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
				System.out.println("[ERROR] 잘못된 기능입니다.");
				System.out.println();
			}

			if (option.equals("1")) {
				handleSelectCoursePage();
			} else if (option.equals("Q")) {
				break;
			}
		}
	}

	public void handleSelectCoursePage() {
		subway.selectPage();
		String option = input.inputOption();

		if(!option.equals("1") && !option.equals("2") && !option.equals("B")) {
			System.out.println("[ERROR] 잘못된 기능입니다.");
			System.out.println();
			handleSelectCoursePage();
		}

		if (option.equals("1")) {
			String start = input.inputStation("출발역을 입력하세요.");
			String end = input.inputStation("도착역을 입력하세요.");
			subway.shortDistancePath(start, end);
		} else if (option.equals("2")) {
			String start = input.inputStation("출발역을 입력하세요.");
			String end = input.inputStation("도착역을 입력하세요.");
			subway.shortTimePath(start, end);
		}
	}
}
