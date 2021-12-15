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

	@Override
	public String toString() {
		return "PathResult{" +
			"path=" + path +
			", totalTime=" + totalTime +
			", totalDistance=" + totalDistance +
			'}';
	}
}
