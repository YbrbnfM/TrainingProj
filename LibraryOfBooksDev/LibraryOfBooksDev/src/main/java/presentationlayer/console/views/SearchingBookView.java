package presentationlayer.console.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import entities.Book;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import presentationlayer.controllers.BookController;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchingBookView extends ConsoleView {
	BookController bc = new BookController();

	@Override
	public void onLoad() {
		super.onLoad();
		messages.add("Использовать следующие флаги для поиска книг:\n" + "\\n - по названию (\\n название_книги)"
				+ "\n\\a - по автору (\\a first_name last_name, first_name1 last_name1),(\\a [id_автора])");
	}

	@Override
	public void onRun() {
		mes = in.nextLine();
		List<Book> books;
		try {
			books = findBooks(mes);
			if (!books.isEmpty())
				messages = books.stream().map(x -> x.toString()).collect(Collectors.toList());
			else
				messages = Arrays.asList("По заданным критериям книги не найдены");
		} catch (IllegalArgumentException e) {
			exc = e;
			super.onRun();
		}
	}

	private List<Book> findBooks(String mes) throws IllegalArgumentException {
		List<String> criterials = parseMes(mes);
		List<String> fNames = new ArrayList<>();
		List<String> lNames = new ArrayList<>();
		if (!criterials.get(2).isBlank()) {
			List<String> tmpANames = Arrays.asList(criterials.get(2).split(", "));
			for (int i = 0; i < tmpANames.size(); i++)
				if (!tmpANames.get(i).matches("[A-Za-zА-Яа-я]+ [A-Za-zА-Яа-я]+"))
					throw new IllegalArgumentException("Некоректный входные данные");

			fNames = tmpANames.stream().map(a -> a.split(" ")[0]).collect(Collectors.toList());
			lNames = tmpANames.stream().map(a -> a.split(" ")[1]).collect(Collectors.toList());
		}
		if (criterials.get(0).isBlank() && criterials.get(1).isBlank() && criterials.get(2).isBlank()
				|| (!criterials.get(1).isBlank() && !criterials.get(1).matches("[0-9]*")))
			throw new IllegalArgumentException("Некоректный входные данные");

		List<String> cacheFName = fNames; // ?????????
		List<String> cacheLNames = lNames; // ?????????
		return bc.get((Predicate<Book>) (x -> (criterials.get(0).isBlank() || matchBookName(x, criterials.get(0)))
				&& (criterials.get(1).isBlank() || matchIdA(x, criterials.get(1)))
				&& (criterials.get(2).isBlank() || matchFLNamesA(x, cacheFName, cacheLNames))));
	}

	private boolean matchBookName(Book x, String name) {
		return x.getName().equals(name);
	}

	private boolean matchIdA(Book x, String ids) throws IllegalArgumentException {
		try {
			int id = Integer.parseInt(ids);
			return x.getAuthors().stream().filter(y -> y.getId() == id).findAny().isPresent();
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean matchFLNamesA(Book x, List<String> fNames, List<String> lNames) {
		if (fNames.isEmpty() || lNames.isEmpty())
			return false;
		return x.getAuthors().stream()
				.filter(y -> fNames.contains(y.getFirstname()) || lNames.contains(y.getLastname())).findAny()
				.isPresent();
	}

	private List<String> parseMes(String mes) {
		List<String> criterials = Arrays.asList("", "", "");
		mes = mes.replace('\\', '/');
		String[] tmp = mes.split("/");
		for (int i = 1; i < tmp.length; i++) {
			if (tmp[i].substring(0, 1).equals("n"))
				criterials.set(0, tmp[i].substring(2));
			else if (tmp[i].substring(0, 1).equals("a"))
				if (tmp[i].contains("["))
					criterials.set(1, tmp[i].substring(tmp[i].indexOf('[') + 1, tmp[i].indexOf(']')));
				else
					criterials.set(2, tmp[i].substring(2));
		}
		return criterials;
	}
}
