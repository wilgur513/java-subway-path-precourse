package subway.domain;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.jgrapht.GraphPath;

public class SubwayDistanceMap extends SubwayMap {
	@Override
	protected int getTotalTime(GraphPath path) {
		return findTotalTime(path.getVertexList());
	}

	private int findTotalTime(List<String> vertexList) {
		int totalTime = 0;
		String left = vertexList.get(0);

		for (int i = 1; i < vertexList.size(); i++) {
			String right = vertexList.get(i);
			totalTime += findLessTimeCloseStation(left, right).getTime();
			left = right;
		}

		return totalTime;
	}

	private CloseStation findLessTimeCloseStation(String left, String right) {
		return LineRepository.lines().stream()
			.map(Line::getCloseStations)
			.flatMap(Collection::stream)
			.filter(s -> s.isSameCloseStation(left, right))
			.sorted(Comparator.comparingInt(CloseStation::getTime))
			.findFirst().get();
	}

	@Override
	protected int getTotalDistance(GraphPath path) {
		return (int) path.getWeight();
	}

	@Override
	protected int getEdgeWeight(CloseStation closeStation) {
		return closeStation.getDistance();
	}
}
