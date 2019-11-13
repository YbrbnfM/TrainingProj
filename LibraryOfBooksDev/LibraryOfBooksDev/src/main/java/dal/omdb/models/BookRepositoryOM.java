package dal.omdb.models;

import java.util.List;
import java.util.function.Predicate;

import dal.Repositorable;
import entities.Book;

public class BookRepositoryOM implements Repositorable<Book> {

	@Override
	public List<Book> getAll() {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public Book get(int i) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public Book get(Predicate<Book> p) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public void create(Book elem) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public void delete(int i) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public void update(Book elem) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

}
