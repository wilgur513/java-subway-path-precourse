package subway.domain;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class SubwayMap {
	private final WeightedMultigraph<String, DefaultWeightedEdge> graphForDistance =
		new WeightedMultigraph(DefaultWeightedEdge.class);
	private final WeightedMultigraph<String, DefaultWeightedEdge> graphForTime =
		new WeightedMultigraph(DefaultWeightedEdge.class);

	public SubwayMap() {
		initVertex();
		initEdge();
	}

	private void initVertex() {
		StationRepository.stations()
			.stream()
			.forEach(s -> {
				graphForDistance.addVertex(s.getName());
				graphForTime.addVertex(s.getName());
			});
	}

	private void initEdge() {
		for (Line line : LineRepository.lines()) {
			initEdgeByEachLine(line);
		}
	}

	private void initEdgeByEachLine(Line line) {
		for (CloseStation closeStation : line.getCloseStations()) {
			initEdgeByDistance(edgeForDistance(closeStation), closeStation.getDistance());
			initEdgeByTime(edgeForTime(closeStation), closeStation.getTime());
		}
	}

	private DefaultWeightedEdge edgeForTime(CloseStation closeStation) {
		return graphForTime.addEdge(closeStation.getLeftStationName(), closeStation.getRightStationName());
	}

	private DefaultWeightedEdge edgeForDistance(CloseStation closeStation) {
		return graphForDistance.addEdge(closeStation.getLeftStationName(), closeStation.getRightStationName());
	}

	private void initEdgeByTime(DefaultWeightedEdge edge, int time) {
		graphForTime.setEdgeWeight(edge, time);
	}

	private void initEdgeByDistance(DefaultWeightedEdge edge, int distance) {
		graphForDistance.setEdgeWeight(edge, distance);
	}

	public PathResult getPathResultByShortestDistance(String start, String end) {
		DijkstraShortestPath shortestPath = new DijkstraShortestPath(graphForDistance);
		List<String> vertexList = shortestPath.getPath(start, end).getVertexList();
		return new PathResult(vertexList, findTotalTime(vertexList), (int) shortestPath.getPath(start, end).getWeight());
	}

	private int findTotalTime(List<String> vertexList) {
		int totalTime = 0;
		String left = vertexList.get(0);

		for (int i = 1; i < vertexList.size(); i++) {
			String right = vertexList.get(i);
			CloseStation closeStation = findCloseStation(left, right);
			totalTime += closeStation.getTime();
			left = right;
		}

		return totalTime;
	}

	private CloseStation findCloseStation(String left, String right) {
		return LineRepository.lines().stream()
			.map(Line::getCloseStations)
			.flatMap(Collection::stream)
			.filter(s -> s.isSameCloseStation(left, right))
			.sorted(Comparator.comparingInt(CloseStation::getTime))
			.findFirst().get();
	}
}
