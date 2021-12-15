package subway.domain;

public class CloseStation {
	private final Station left;
	private final Station right;
	private final int distance;
	private final int time;

	private CloseStation(Station left, Station right, int distance, int time) {
		this.left = left;
		this.right = right;
		this.distance = distance;
		this.time = time;
	}

	public static CloseStation of(Station left, Station right, int distance, int time) {
		return new CloseStation(left, right, distance, time);
	}

	public boolean isSameCloseStation(String left, String right) {
		return getLeftStationName().equals(left) && getRightStationName().equals(right) ||
			getRightStationName().equals(left) && getLeftStationName().equals(right);
	}

	public String getLeftStationName() {
		return left.getName();
	}

	public String getRightStationName() {
		return right.getName();
	}

	public int getDistance() {
		return distance;
	}

	public int getTime() {
		return time;
	}
}
