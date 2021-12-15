package subway;

import subway.controller.SubwayController;
import subway.domain.CloseStation;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class Application {
	public static void main(String[] args) {
		saveInitialData();
		SubwayController controller = new SubwayController();
		controller.service();
	}

	private static void saveInitialData() {
		saveStations();
		saveLines();
		saveCloseStations();
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

	private static void saveCloseStations() {
		saveLine1();
		saveLine2();
		saveLine3();
	}

	private static void saveLine1() {
		Line line1 = LineRepository.findByName("2호선");
		line1.addCloseStation(CloseStation.of(findStationByName("교대역"), findStationByName("강남역"), 2, 3));
		line1.addCloseStation(CloseStation.of(findStationByName("강남역"), findStationByName("역삼역"), 2, 3));
	}

	private static void saveLine2() {
		Line line2 = LineRepository.findByName("3호선");
		line2.addCloseStation(CloseStation.of(findStationByName("교대역"), findStationByName("남부터미널역"), 3, 2));
		line2.addCloseStation(CloseStation.of(findStationByName("남부터미널역"), findStationByName("양재역"), 6, 5));
		line2.addCloseStation(CloseStation.of(findStationByName("양재역"), findStationByName("매봉역"), 1, 1));
	}

	private static void saveLine3() {
		Line line3 = LineRepository.findByName("신분당선");
		line3.addCloseStation(CloseStation.of(findStationByName("강남역"), findStationByName("양재역"), 2, 8));
		line3.addCloseStation(CloseStation.of(findStationByName("양재역"), findStationByName("양재시민의숲역"), 10, 3));
	}

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name);
	}
}
