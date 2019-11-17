package dal;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import lombok.NonNull;

/**
 * @author 8apri_000
 *
 */
public interface Repositorable <T>{
	/**
	 * Вернуть объект по ID
	 */
	T get(int id) throws NoSuchElementException;
	List<T> get(@NonNull Predicate<T> p);
	void create(@NonNull T element);
	/**
	 * Удалить объект по ID
	 */
	T delete(int id) throws NoSuchElementException;
	List<T> delete (@NonNull Predicate<T> p);
	void update(@NonNull T element);
}
