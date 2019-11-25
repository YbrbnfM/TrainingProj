package pl.models;

import java.util.ArrayDeque;
import java.util.Deque;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.Model;
import pl.ViewEnum;
import pl.console.views.ConsoleView;
import utility.Triple;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class MainMenuModel extends DefaultModel implements Model{
	Deque<Triple<Integer, String, Class<? extends ConsoleView>>> menus = new ArrayDeque<>();

	@SuppressWarnings("unchecked")
	public MainMenuModel() {
		ViewEnum[] cache = ViewEnum.values();
		for (int i = 1; i < cache.length; i++)
			menus.addLast(new Triple<Integer, String,Class<? extends ConsoleView>>(cache[i].getValue().getKey(), cache[i].getValue().getValue(),cache[i].getValue().getValue2()));
	}

	@Override
	public String toString() {
		return new String(menus.stream().map(x->x.toString()).reduce((x,y)->(x+"\n"+y)).get());
	}

}
