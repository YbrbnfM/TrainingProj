package presentationlayer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import businesslogiclayer.BookService;
import entities.Author;
import entities.Book;
import entities.Category;
import lombok.NonNull;
import presentationlayer.Controller;

public class BookController implements Controller<Book> {
	BookService bs = new BookService();

	@Override
	public Book get(int id) throws NoSuchElementException {
		try {
			return bs.get(id);
		} catch (Exception e) {
			throw new NoSuchElementException("Не найдено");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> get(@NonNull Predicate<?> p) throws IllegalArgumentException {
		try {
			return bs.get((Predicate<entities.Book>) p);
		} catch (Exception e) {
			throw new IllegalArgumentException("Неверный фильтр");
		}

	}

	@Override
	public void put(@NonNull Book t) throws IllegalArgumentException {
		bs.update(t);
	}

	@Override
	public void post(@NonNull Book t) throws IllegalArgumentException {
		try {
			bs.create(t);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Некорректные входные данные");
		}
	}

	@Override
	public void delete(int id) throws NoSuchElementException {
		try {
			bs.delete(id);
		} catch (NoSuchElementException e) {		
			// Ничего не делать		
		}
	}

	@Override
	public void put(@NonNull String t) throws IllegalArgumentException {
		String[] args = t.split(" ");
		Category c = new Category();
		List<Author> authors = new ArrayList<>();
		String name = "";
		int idb = 0;
		if (args.length != 4)
			throw new IllegalArgumentException("Некорректные входные данные");
		for (int i = 0; i < args.length; i++) {
			String[] tmp = args[i].split(":");
			if (tmp.length != 2)
				throw new IllegalArgumentException("Некорректные входные данные");
			if (tmp[0].equalsIgnoreCase("name")) {
				name = tmp[1];
				continue;
			}
			if (tmp[0].equalsIgnoreCase("id")) {
				idb = Integer.parseInt(tmp[1]);
				continue;
			}
			if (tmp[0].equalsIgnoreCase("category")) {
				String[] argsC = tmp[1].split(",");
				// Category c;
				if (argsC.length != 2)
					throw new IllegalArgumentException("Некорректные входные данные");
				String id = "";
				String description = "";
				for (int j = 0; j < argsC.length; j++) {
					String[] tmpC = argsC[j].split("--");
					if (tmpC.length != 2)
						throw new IllegalArgumentException("Некорректные входные данные");
					if (tmpC[0].equalsIgnoreCase("id")) {
						id = tmpC[1];
						continue;
					}
					if (tmpC[0].equalsIgnoreCase("description")) {
						description = tmpC[1];
						continue;
					}
					throw new IllegalArgumentException("Некорректные входные данные");
				}
				c = new Category(id, description);
				continue;
			}
			if (tmp[0].equalsIgnoreCase("authors")) {
				String[] tmpAs = tmp[1].split(";");
				if (tmpAs.length == 0)
					throw new IllegalArgumentException("Некорректные входные данные");
				for (int j = 0; j < tmpAs.length; j++) {
					String[] tmpA = tmpAs[j].split(",");
					Author a = new Author();
					if (tmpA.length != 2)
						throw new IllegalArgumentException("Некорректные входные данные");
					for (int k = 0; k < tmpA.length; k++) {
						if (tmpA[k].matches("[A-Za-zА-Яа-я]+--[A-Za-zА-Яа-я]+")) {
							String[] tmpAF = tmpA[k].split("--");
							if (tmpAF[0].equalsIgnoreCase("firstname")) {
								a.setFirstname(tmpAF[1]);
								continue;
							}
							if (tmpAF[0].equalsIgnoreCase("lastname")) {
								a.setLastname(tmpAF[1]);
								continue;
							}
						}
						throw new IllegalArgumentException("Некорректные входные данные");
					}
					authors.add(a);
				}
				continue;
			}
			throw new IllegalArgumentException("Некорректные входные данные");
		}
		bs.update(new Book(idb, name, c, true, authors));
	}

	@Override
	public void post(@NonNull String t) throws IllegalArgumentException {
		Book b = new Book();
		String[] args = t.split(" ");
		Category c = new Category();
		List<Author> authors = new ArrayList<>();
		String name = "";
		if (args.length != 3)
			throw new IllegalArgumentException("Некорректные входные данные");
		for (int i = 0; i < args.length; i++) {
			String[] tmp = args[i].split(":");
			if (tmp.length != 2)
				throw new IllegalArgumentException("Некорректные входные данные");
			if (tmp[0].equalsIgnoreCase("name")) {
				name = tmp[1];
				continue;
			}
			if (tmp[0].equalsIgnoreCase("category")) {
				String[] argsC = tmp[1].split(",");
				// Category c;
				if (argsC.length != 2)
					throw new IllegalArgumentException("Некорректные входные данные");
				String id = "";
				String description = "";
				for (int j = 0; j < argsC.length; j++) {
					String[] tmpC = argsC[j].split("--");
					if (tmpC.length != 2)
						throw new IllegalArgumentException("Некорректные входные данные");
					if (tmpC[0].equalsIgnoreCase("id")) {
						id = tmpC[1];
						continue;
					}
					if (tmpC[0].equalsIgnoreCase("description")) {
						description = tmpC[1];
						continue;
					}
					throw new IllegalArgumentException("Некорректные входные данные");
				}
				c = new Category(id, description);
				continue;
			}
			if (tmp[0].equalsIgnoreCase("authors")) {
				String[] tmpAs = tmp[1].split(";");
				if (tmpAs.length == 0)
					throw new IllegalArgumentException("Некорректные входные данные");
				for (int j = 0; j < tmpAs.length; j++) {
					String[] tmpA = tmpAs[j].split(",");
					Author a = new Author();
					if (tmpA.length != 2)
						throw new IllegalArgumentException("Некорректные входные данные");
					for (int k = 0; k < tmpA.length; k++) {
						if (tmpA[k].matches("[A-Za-zА-Яа-я]+--[A-Za-zА-Яа-я]+")) {
							String[] tmpAF = tmpA[k].split("--");
							if (tmpAF[0].equalsIgnoreCase("firstname")) {
								a.setFirstname(tmpAF[1]);
								continue;
							}
							if (tmpAF[0].equalsIgnoreCase("lastname")) {
								a.setLastname(tmpAF[1]);
								continue;
							}
						}
						throw new IllegalArgumentException("Некорректные входные данные");
					}
					authors.add(a);
				}
				continue;
			}
			throw new IllegalArgumentException("Некорректные входные данные");
		}
		b.setCategory(c);
		b.setAuthors(authors);
		b.setName(name);
		bs.create(b);
	}
}
