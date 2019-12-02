package dataaccesslayer.omdb;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import customAnnotations.marker.IsReplicate;
import entities.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OMDataBase {
	static int idObject = 100;
	@Getter
	List<Author> authors = Arrays.asList(new Author(1, "FAa", "LAa"), new Author(2, "FAb", "LAb"),
			new Author(3, "FAc", "LAc"), new Author(4, "FAd", "LAd"));
	@Getter
	List<Client> clients = new ArrayList<>();
	@Getter
	List<AccountingRecord> accountingRecords = new ArrayList<>();
	@Getter
	List<Category> categories = Arrays.asList(new Category("C1", "C1 description"),
			new Category("C2", "C2 description"));
	@Getter
	List<Book> books = new ArrayList<>(
			Arrays.asList(new Book(1, "B1", categories.get(0), true, Arrays.asList(authors.get(0), authors.get(1))),
					new Book(2, "B2", categories.get(1), true, Arrays.asList(authors.get(2), authors.get(3)))));

	static final OMDataBase instance = new OMDataBase();

	public static OMDataBase getInstance() {
		return instance;
	}

	public <T> void generateId(T o) {
		try {
			Field f = o.getClass().getDeclaredField("id");
			f.setAccessible(true);
			f.setInt(o, idObject++);
			f.setAccessible(false);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			try {
				Field f = o.getClass().getSuperclass().getDeclaredField("id");
				f.setAccessible(true);
				f.setInt(o, idObject++);
				f.setAccessible(false);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
				e1.printStackTrace();
				e.printStackTrace();
				throw new IllegalArgumentException("Not found or not access to the field \"id\"");
			}
		}
	}

	// если объект по какойто причине был удален во время редактирования - он
	// восстанавливается в измененном виде
	// !!! Целесообразнанно ли писать такие методы для сокращения кода, используя
	// рефлексию
	@SuppressWarnings("unchecked")
	public <T> void update(T o, List<T> lst) {
		Optional<T> tmpOptional = lst.stream().filter(x -> {
			try {
				return x.getClass().getDeclaredField("id").getInt(x) == o.getClass().getDeclaredField("id").getInt(o);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Not found or not access to the field \"id\"");
			}
		}).findAny();
		try {
			if (tmpOptional.isPresent())

				lst.set(lst.indexOf(tmpOptional.get()), (T) Arrays.asList(o.getClass().getConstructors()).stream()
						.filter(x -> x.getAnnotation(IsReplicate.class) != null).findAny().get().newInstance(o));
			else
				lst.add((T) Arrays.asList(o.getClass().getConstructors()).stream()
						.filter(x -> x.getAnnotation(IsReplicate.class) != null).findAny().get().newInstance(o));
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Not found or not access to the constructor for copy");
		}
	}
}