package pl.models;

import entities.Book;
import lombok.Getter;
import pl.Model;

public class BookModel implements Model {
	@Getter
	Book book;
	public BookModel() {}
	public BookModel(Book book) {
		this.book = new Book(book);
	}
	@Override
	public String toString() {
		return book.toString();
	}
}
