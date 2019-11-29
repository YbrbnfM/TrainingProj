package entities;

import lombok.Getter;

public enum StatusAR {
	OPENED("Opened"),
	CLOSED("Closed");

	@Getter
	private String value;

	private StatusAR(String value) {
		this.value = value;
	}
}