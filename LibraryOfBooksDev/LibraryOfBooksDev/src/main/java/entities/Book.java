package entities;

import java.util.ArrayList;
import java.util.List;
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
	@NonNull
	List<Author> authors = new ArrayList<>();
}