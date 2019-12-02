package businesslogiclayer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import businesslogiclayer.config.Repositories;
import dataaccesslayer.Repositorable;
import entities.AccountingRecord;
import entities.Book;
import lombok.NonNull;

public class BookService {
	@SuppressWarnings("unchecked")
	Repositorable<Book> bookRepositorable = (Repositorable<Book>) Repositories.BOOK_REPOSITOTY.getValue();
	@SuppressWarnings("unchecked")
	Repositorable<AccountingRecord> arRepositorable = (Repositorable<AccountingRecord>) Repositories.ACCOUNTING_RECORD_REPOSITORY
			.getValue();

	public void create(@NonNull Book c) throws IllegalArgumentException/* throws DuplicateParameterException */ {
		if (c.getCategory() == null || c.getAuthors().size() == 0)
			throw new IllegalArgumentException("Not all data is filled");
		bookRepositorable.create(c);
	}

	public void update(@NonNull Book c) throws IllegalArgumentException {
		if (c.getCategory() == null || c.getAuthors().size() == 0)
			throw new IllegalArgumentException("Not all data is filled");
		if (c.getId() == 0)
			throw new IllegalArgumentException("ID can't be 0");
		try {
			bookRepositorable.update(c);
		} catch (NoSuchElementException e) {
			bookRepositorable.create(c);
		}
	}

	public void delete(int id) throws NoSuchElementException {
		bookRepositorable.delete(id);
		arRepositorable.delete(x -> x.getBookId() == id);
	}

	public void delete(@NonNull Predicate<Book> p) throws NoSuchElementException {
		List<Book> deleted = bookRepositorable.delete(p);
		arRepositorable.delete(x -> deleted.stream().filter(y -> y.getId() == x.getBookId()).count() != 0);
	}

	@NonNull
	public Book get(int id) throws NoSuchElementException {
		return bookRepositorable.get(id);
	}

	@NonNull
	public List<Book> get(Predicate<Book> p) {
		return bookRepositorable.get(p).stream().collect(Collectors.toList());
	}

	@NonNull
	public List<Book> getAll() {
		return bookRepositorable.get(x -> true).stream().collect(Collectors.toList());
	}
}
