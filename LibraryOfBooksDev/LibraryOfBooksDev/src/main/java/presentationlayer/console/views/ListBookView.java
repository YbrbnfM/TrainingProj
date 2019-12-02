package presentationlayer.console.views;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import presentationlayer.controllers.BookController;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListBookView extends ConsoleView {
	BookController bc = new BookController();

	@Override
	public void onLoad() {
		messages = bc.get(x -> true).stream().map(x -> x.toString()).collect(Collectors.toList());
	}

	@Override
	public void onRun() {
		mes = in.nextLine();
		super.onRun();
	}

}
