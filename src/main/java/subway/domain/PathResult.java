package subway.domain;

import java.util.List;

public class PathResult {
	private List<String> path;
	private int totalTime;
	private int totalDistance;

	public PathResult(List<String> path, int totalTime, int totalDistance) {
		this.path = path;
		this.totalTime = totalTime;
		this.totalDistance = totalDistance;
	}

	public List<String> getPath() {
		return path;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public int getTotalDistance() {
		return totalDistance;
	}
}
