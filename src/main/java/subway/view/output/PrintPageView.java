package subway.view.output;

import java.util.Observable;
import java.util.Observer;
import subway.event.Event;
import subway.event.EventType;

public class PrintPageView implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		Event event = (Event) arg;

		if(event.getType() == EventType.PRINT_MAIN_PAGE) {
			printMainPage();
		}

		if(event.getType() == EventType.PRINT_SELECT_COURSE_PAGE) {
			printSelectCourse();
		}
	}

	private void printMainPage() {
		System.out.println("## 메인 화면");
		System.out.println("1. 경로 조회");
		System.out.println("Q. 종료");
		System.out.println();
	}

	private void printSelectCourse() {
		System.out.println("## 경로 기준");
		System.out.println("1. 최단 거리");
		System.out.println("2. 최소 시간");
		System.out.println("B. 돌아가기");
		System.out.println();
	}
}
