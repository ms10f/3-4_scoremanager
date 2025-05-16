package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedErrors {
	private final Map<String, List<String>> errors = new HashMap<>();

	public NamedErrors() {
	}

	public NamedErrors add(String name, String message) {
		List<String> errors = this.errors.getOrDefault(name, new ArrayList<>());
		errors.add(message);

		this.errors.put(name, errors);

		return this;
	}

	public Map<String, List<String>> get() {
		return this.errors;
	}
}
