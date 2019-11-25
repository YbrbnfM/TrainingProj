package pl.console.views;

import pl.controllers.BookController;

public class ListBookView extends ConsoleView {
	BookController bc = new BookController();
	@Override
	public void onLoad() {
		model = bc.get(x->true);
	}
	
	@Override
	public void onRun() {
		if(in.next().equals("\\back"))
			isStoped = true;
	}
	
}
