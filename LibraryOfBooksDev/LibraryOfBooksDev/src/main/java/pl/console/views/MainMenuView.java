package pl.console.views;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.ViewEnum;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainMenuView extends ConsoleView {
	@Override
	public void onLoad() {
		messages = Arrays.asList(ViewEnum.values()).stream().skip(1)
				.map(x -> new String(x.getValue().getKey() + " " + x.getValue().getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public void onRun() {
		mes = in.nextLine();
		try {
			int keyLink = Integer.parseInt(mes);
			link = Arrays.asList(ViewEnum.values()).stream().filter(x -> x.getValue().getKey() == keyLink).findAny()
					.get();
			isStoped = true;
		} catch (NumberFormatException e) {
			super.onRun();
		} catch (NoSuchElementException e) {
			System.out.println("No such element in menu");
		}
	}
}
