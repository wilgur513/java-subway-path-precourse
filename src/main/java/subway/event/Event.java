package subway.event;

import subway.domain.PathResult;

public class Event<T> {
	private EventType type;
	private T data;

	public Event(EventType type) {
		this.type = type;
	}

	public Event(EventType type, T data) {
		this.type = type;
		this.data = data;
	}

	public EventType getType() {
		return type;
	}

	public T getData() {
		return data;
	}
}
