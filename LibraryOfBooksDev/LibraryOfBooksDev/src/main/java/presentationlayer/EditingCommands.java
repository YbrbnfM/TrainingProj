package presentationlayer;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;
import utility.Pair;

public enum EditingCommands {
	CREATE(new Pair<>(1, "Создание")),
	EDIT(new Pair<>(2, "Редактирование")),
	DELETE(new Pair<>(3, "Удаление"));

	@Getter
	final Pair<Integer, String> value;

	private EditingCommands(Pair<Integer, String> value) {
		this.value = value;
	}

	public static EditingCommands getByKey(int key) throws NoSuchElementException {
		return Arrays.asList(EditingCommands.values()).stream().filter(x -> x.getValue().getKey() == key).findAny()
				.get();
	}
}
