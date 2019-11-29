package dal.omdb;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import dal.Repositorable;
import entities.Book;
import lombok.NonNull;

public class BookRepositoryOM implements Repositorable<Book> {
	//private final Logger log = LogManager.getLogger();
	private OMDataBase db = OMDataBase.getInstance();
	private List<Book> cachedLink = db.getBooks();

	@Override
	public Book get(int id) throws NoSuchElementException {
		return new Book(cachedLink.stream().filter(x -> x.getId() == id).findAny().get());
	}

	@Override
	public List<Book> get(@NonNull Predicate<Book> p) {
		return cachedLink.stream().filter(p).map(x -> new Book(x)).collect(Collectors.toList());
	}

	@Override
	public void create(@NonNull Book element) {
		db.generateId(element);
		cachedLink.add(new Book(element));
	}

	@Override
	public Book delete(int id) throws NoSuchElementException {
		Optional<Book> tmpOptional = cachedLink.stream().filter(x -> x.getId() == id).findAny();
		cachedLink.remove(tmpOptional.get());
		return tmpOptional.get();
	}

	@Override
	public List<Book> delete(@NonNull Predicate<Book> p) {
		List<Book> remElements = new ArrayList<>();
		cachedLink.stream().filter(p).forEach(x -> remElements.add(delete(x.getId())));
		return remElements;
	}

	@Override
	public void update(@NonNull Book element) throws NoSuchElementException {
		// db.update(element, db.getBooks());
//		try {
		Book findedElement = cachedLink.stream().filter(x -> x.getId() == element.getId()).findAny().get();
		cachedLink.set(cachedLink.indexOf(findedElement), element);
//		} catch (NoSuchElementException nsee) {
//			cachedLink.add(element);
//			log.warn("Updated element does not exist, but was re-created");
//		}
	}

}
