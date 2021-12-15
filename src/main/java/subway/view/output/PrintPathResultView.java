package subway.view.output;

import java.util.Observable;
import java.util.Observer;
import subway.domain.PathResult;
import subway.event.Event;
import subway.event.EventType;

public class PrintPathResultView implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		Event<PathResult> event = (Event) arg;

		if(event.getType() == EventType.PRINT_PATH_RESULT) {
			PathResult pathResult = event.getData();
			System.out.println("## 조회 결과");
			System.out.println("[INFO] ---");
			System.out.println("[INFO] 총 거리: " + pathResult.getTotalDistance() + "km");
			System.out.println("[INFO] 총 소요 시간: " + pathResult.getTotalTime() + "분");
			System.out.println("[INFO] ---");
			pathResult.getPath().stream().forEach(s -> System.out.println("[INFO] " + s));
			System.out.println();
		}
	}
}
