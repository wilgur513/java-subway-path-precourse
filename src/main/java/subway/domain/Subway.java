package subway.domain;

import java.util.Observable;
import subway.event.EventType;

public class Subway extends Observable {
	public void mainPage() {
		setChanged();
		notifyObservers(EventType.PRINT_MAIN_PAGE);
	}

	public void selectPage() {
		setChanged();
		notifyObservers(EventType.PRINT_SELECT_COURSE_PAGE);
	}

	public void shortDistancePath(String start, String end) {

	}
}
