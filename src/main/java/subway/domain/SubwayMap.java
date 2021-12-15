package subway.domain;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public abstract class SubwayMap {
	protected final WeightedMultigraph<String, DefaultWeightedEdge> graph =
		new WeightedMultigraph(DefaultWeightedEdge.class);

	public SubwayMap() {
		initVertex();
		initEdge();
	}

	private void initVertex() {
		StationRepository.stations()
			.stream()
			.forEach(s -> graph.addVertex(s.getName()));
	}

	private void initEdge() {
		for (Line line : LineRepository.lines()) {
			initEdgeByEachLine(line);
		}
	}

	private void initEdgeByEachLine(Line line) {
		for (CloseStation closeStation : line.getCloseStations()) {
			graph.setEdgeWeight(edge(closeStation), getEdgeWeight(closeStation));
		}
	}

	private DefaultWeightedEdge edge(CloseStation closeStation) {
		return graph.addEdge(closeStation.getLeftStationName(), closeStation.getRightStationName());
	}

	public PathResult getPathResult(String start, String end) {
		return new PathResult(getShortestPath().getPath(start, end).getVertexList(),
			getTotalDistance(getShortestPath().getPath(start, end)),
			getTotalTime(getShortestPath().getPath(start, end)));
	}

	private DijkstraShortestPath getShortestPath() {
		return new DijkstraShortestPath(graph);
	}

	protected abstract int getTotalTime(GraphPath shortestPath);

	protected abstract int getTotalDistance(GraphPath shortestPath);

	protected abstract int getEdgeWeight(CloseStation closeStation);

	public static SubwayMap createSubwayMap(String option) {
		if (option.equals("1")) {
			return new SubwayDistanceMap();
		}
		return new SubwayTimeMap();
	}
}
