package pl;

import java.util.Arrays;
import java.util.NoSuchElementException;

import lombok.Getter;
import utility.Pair;

public enum EditableEntities {
	CLIENTS(new Pair<>(1, "Клиенты")),
	BOOKS(new Pair<>(2, "Книги")),
	ACCOUNTING_RECORDS(new Pair<>(3, "Учетные записи"));

	@Getter
	final Pair<Integer, String> value;

	private EditableEntities(Pair<Integer, String> value) {
		this.value = value;
	}

	public static EditableEntities getByKey(int key) throws NoSuchElementException {
		return Arrays.asList(EditableEntities.values()).stream().filter(x -> x.getValue().getKey() == key).findAny()
				.get();
	}
}
