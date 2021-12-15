package subway.domain;

import java.util.Observable;
import subway.event.Event;
import subway.event.EventType;

public class Subway extends Observable {
	private final SubwayMap map = new SubwayMap();

	public void mainPage() {
		setChanged();
		notifyObservers(new Event(EventType.PRINT_MAIN_PAGE));
	}

	public void selectPage() {
		setChanged();
		notifyObservers(new Event(EventType.PRINT_SELECT_COURSE_PAGE));
	}

	public void shortDistancePath(String start, String end) {
		PathResult pathResult = map.getPathResultByShortestDistance(start, end);
		setChanged();
		notifyObservers(new Event(EventType.PRINT_PATH_RESULT, pathResult));
	}

	public void shortTimePath(String start, String end) {
		PathResult pathResult = map.getPathResultByShortestTime(start, end);
		setChanged();
		notifyObservers(new Event(EventType.PRINT_PATH_RESULT, pathResult));
	}
}
