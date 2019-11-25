

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import pl.ViewEnum;
import pl.console.views.ConsoleView;
import pl.console.views.MainMenuView;

public class Application {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//List<Class> views = new ArrayList<>(); //переделать: считать классы из пакета views
		ConsoleView currentView = new MainMenuView();
		while (currentView.getLink()!=null || !currentView.isStoped()) {
			currentView.onLoad();
			currentView.render();
			currentView.onRun();
			if(currentView.getLink()!=null && currentView.isStoped()) {
				try {
					Class<ConsoleView> c = (Class<ConsoleView>) currentView.getClass();
					ViewEnum newLink = Arrays.asList(ViewEnum.values()).stream().filter(x->x.getValue().getValue2().equals(c)).findAny().get();
					currentView = (ConsoleView) currentView.getLink().getValue().getValue2().getDeclaredConstructor().newInstance();
					currentView.setLink(newLink);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
