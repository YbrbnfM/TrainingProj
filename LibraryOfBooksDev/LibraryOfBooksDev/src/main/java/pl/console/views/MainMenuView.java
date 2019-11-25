package pl.console.views;

import java.util.Arrays;
import java.util.NoSuchElementException;
import pl.ViewEnum;
import pl.models.MainMenuModel;

public class MainMenuView extends ConsoleView {
	@Override
	public	void onLoad() {
		super.onLoad();
		model = new MainMenuModel();
//		model.setStrs(Arrays.asList(ViewEnum.values()).stream().skip(1).map(x -> x.getValue().getValue())
//				.collect(Collectors.toList()));
	}

	@Override
	public void onRun() {
//		String mes = in.next();
		try {
			int keyLink = Integer.parseInt(mes);
			link = Arrays.asList(ViewEnum.values()).stream().filter(x -> x.getValue().getKey() == keyLink).findAny()
					.get();
			isStoped = true;
		} catch (NumberFormatException e) {
			super.onRun();
//			if (mes.equalsIgnoreCase("exit")) {
//				link = null;
//				isStoped = true;
//			} else
//				System.out.println("Illegal Command");

		} catch (NoSuchElementException e) {
			System.out.println("No such element in menu");
		}
	}
}
