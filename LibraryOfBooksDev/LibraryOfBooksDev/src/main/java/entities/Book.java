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

@Data // нарушение инкапсуляции для сложных объектов
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book implements Editable {
	@Setter(AccessLevel.PRIVATE)
	int id;
	@NonNull
	String name;
	@NonNull
	Category category;
	@Setter(AccessLevel.PRIVATE)
	boolean isAvailable;
	@NonNull
	List<Author> authors = new ArrayList<>();

	@IsReplicate
	public Book(@NonNull Book b) {
		this.isAvailable = b.isAvailable;
		this.category = b.category;
		this.id = b.id;
		this.name = b.name;
		this.authors = b.authors.stream().map(x -> new Author(x)).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return new String("" + id + "\t" + "Name: " + name + "\t" + "Category: " + category.getId() + "\t" + "Authors: "
				+ authors.stream().map(x -> x.toString()).reduce((x, y) -> x + ", " + y).get());
	}

}