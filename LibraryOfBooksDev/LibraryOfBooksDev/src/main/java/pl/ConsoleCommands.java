package pl;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

public enum ConsoleCommands {
	EXIT("\\exit"),
	BACK("\\back"),
	HELP("\\help"),
	CLIENTS("\\clients"),
	ACCOUNTING_RECORDS("\\ars");

	@Getter
	final String value;

	public static ConsoleCommands getCommand(String value) throws NoSuchElementException {
		return Arrays.asList(ConsoleCommands.values()).stream().filter(x -> x.getValue().equals(value)).findAny().get();
	}

	ConsoleCommands(String value) {
		this.value = value;
	}
}
