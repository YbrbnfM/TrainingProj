package presentationlayer.console.views;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import presentationlayer.ConsoleCommands;
import presentationlayer.ViewEnum;
import presentationlayer.controllers.AccountingRecordController;
import presentationlayer.controllers.ClientController;

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
	Exception exc;

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
			case CLIENTS:
				messages = new ClientController().get(x -> true).stream().map(x -> x.toString()).collect(Collectors.toList());
				break;
			case ACCOUNTING_RECORDS:
				messages = new AccountingRecordController().get(x -> true).stream().map(x -> x.toString()).collect(Collectors.toList());
				break;
			case BACK:
				if (link != null)
					isStoped = true;
				else
					System.out.println("Невозможно вернуться к предыдущему представлению");
			}
		} catch (NoSuchElementException e) {
			if(exc!=null)
				if(exc.getMessage()!=null) {
					System.out.println(exc.getMessage());
					return;
				}
			System.out.println("Некоректные входные данные");
		}
	}
}
