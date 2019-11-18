package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import customAnnotations.marker.IsReplicate;
import entities.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data //нарушение инкапсуляции для сложных объектов
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
	@Setter(AccessLevel.PRIVATE)
	int id;
	@NonNull
	String name;
	@NonNull
	String categoryId;
	@Setter(AccessLevel.PRIVATE)
	boolean isAvailable;
	@NonNull
	List<Author> authors = new ArrayList<>();
	@IsReplicate
	public Book(@NonNull Book b) {
		this.isAvailable = b.isAvailable;
		this.categoryId = b.categoryId;
		this.id = b.id;
		this.name = b.name;
		this.authors = b.authors.stream().map(x->new Author(x)).collect(Collectors.toList());
	}
}