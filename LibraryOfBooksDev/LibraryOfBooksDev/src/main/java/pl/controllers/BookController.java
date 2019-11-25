package pl.controllers;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import bll.BookService;
import pl.Controller;
import pl.Model;
import pl.models.BookModel;
import pl.models.ListBookModel;

public class BookController implements Controller<Model> {
	BookService bs = new BookService();

	@Override
	public Model get(int id) throws NoSuchElementException {
		try {
			return new BookModel(bs.get(id));
		} catch (Exception e) {
			throw new NoSuchElementException("Not found");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Model get(Predicate<?> p) throws IllegalArgumentException {
		try {
			return new ListBookModel(bs.get((Predicate<entities.Book>) p).stream().map(x -> new BookModel(x))
					.collect(Collectors.toList()));
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filter");
		}

	}

	@Override
	public void put(Model t) throws IllegalArgumentException {
		try {
			bs.update(((BookModel) t).getBook());
		} catch (ClassCastException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal sended data");
		}
	}

	@Override
	public void post(Model t) throws IllegalArgumentException {
		try {
			bs.create(((BookModel) t).getBook());
		} catch (ClassCastException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal sended data");
		}
	}

	@Override
	public void delete(int id) throws NoSuchElementException {
		try {
			bs.delete(id);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Such book was not found");
		}
	}
}
