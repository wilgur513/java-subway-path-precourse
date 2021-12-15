package subway;

import subway.domain.Subway;
import subway.view.input.InputView;
import subway.view.output.PrintPageView;

public class Application {
	private static final InputView input = new InputView();

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
		String option = input.inputOption();

		if (option.equals("1")) {
			handleSelectCoursePage(subway);
		}
	}

	private static void handleSelectCoursePage(Subway subway) {
		subway.selectPage();
		String option = input.inputOption();

		if (option.equals("1")) {
			String start = input.inputStation("출발역을 입력하세요.");
			String end = input.inputStation("도착역을 입력하세요.");
            subway.shortDistancePath(start, end);
		}
	}
}
