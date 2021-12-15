package subway;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.Subway;
import subway.view.input.InputView;
import subway.view.output.PrintPageView;

public class Application {
	private static final InputView input = new InputView();

	public static void main(String[] args) {
		saveStations();
		saveLines();
		Subway subway = initSubway();
		service(subway);
	}

	private static void saveStations() {
		String[] stationNames = {"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
		for (String name : stationNames) {
			StationRepository.addStation(Station.of(name));
		}
	}

	private static void saveLines() {
		String[] lineNames = {"2호선", "3호선", "신분당선"};
		for (String name : lineNames) {
			LineRepository.addLine(Line.of(name));
		}
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
