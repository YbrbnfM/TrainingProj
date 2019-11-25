package pl.console.views;

import java.util.Scanner;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.Model;
import pl.ViewEnum;
import pl.models.DefaultModel;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class ConsoleView {
	@Getter
	@Setter
	ViewEnum link;
	// static String title = "Default view";
	Scanner in = new Scanner(System.in);
	Model model;
	String mes;
	// List<String> messages;
	@Getter
	boolean isStoped;

	public void onLoad() {
		in = new Scanner(System.in);
		model = new DefaultModel();
		// messages = new ArrayList<>();
		isStoped = false;
	}

	public void render() {
		System.out.println(model.toString());
	}

	public void onRun() {
		mes = in.next();
		switch (mes) {
		case "\\exit":
			isStoped = true;
			link = null;
			break;
		case "\\help":
			// прописать инструкцию
			break;
		case "\\back":
			if (link != null)
				isStoped = true;
		}
	}
}
