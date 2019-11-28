package pl.console.views;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.ConsoleCommands;
import pl.ViewEnum;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ConsoleView {
	@Getter
	@Setter
	ViewEnum link;
	// static String title = "Default view";
	Scanner in = new Scanner(System.in);
	// Model model;
	String mes;
	List<String> messages;
	@Getter
	boolean isStoped = false;

	public void onLoad() {
		//in = new Scanner(System.in);
		// model = new DefaultModel();
		messages = new ArrayList<>();
		//isStoped = false;
	}

	public void render() {
		for(int i = 0;i<messages.size();i++)
			System.out.println(messages.get(i));
	}

	public void onRun() {
		try {
			switch (ConsoleCommands.getCommand(mes)) {
			case EXIT:
				isStoped = true;
				link = null;
				break;
			case HELP:
				// прописать инструкцию
				break;
			case BACK:
				if (link != null)
					isStoped = true;
				else
					System.out.println("Невозможно вернуться к предыдущему представлению");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Некоректный входные данные");
		}
	}
}
