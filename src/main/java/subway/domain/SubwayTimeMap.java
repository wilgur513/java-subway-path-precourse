package subway.domain;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.jgrapht.GraphPath;

public class SubwayTimeMap extends SubwayMap {
	@Override
	protected int getTotalTime(GraphPath shortestPath) {
		return (int) shortestPath.getWeight();
	}

	@Override
	protected int getTotalDistance(GraphPath shortestPath) {
		return findTotalDistance(shortestPath.getVertexList());
	}

	private int findTotalDistance(List<String> vertexList) {
		int totalDistance = 0;
		String left = vertexList.get(0);

		for (int i = 1; i < vertexList.size(); i++) {
			String right = vertexList.get(i);
			totalDistance += findLessDistanceCloseStation(left, right).getDistance();
			left = right;
		}

		return totalDistance;
	}

	private CloseStation findLessDistanceCloseStation(String left, String right) {
		return LineRepository.lines().stream()
			.map(Line::getCloseStations)
			.flatMap(Collection::stream)
			.filter(s -> s.isSameCloseStation(left, right))
			.sorted(Comparator.comparingInt(CloseStation::getDistance))
			.findFirst().get();
	}

	@Override
	protected int getEdgeWeight(CloseStation closeStation) {
		return closeStation.getTime();
	}
}
