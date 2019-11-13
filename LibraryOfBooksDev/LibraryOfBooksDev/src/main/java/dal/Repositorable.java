/**
 * 
 */
package dal;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author 8apri_000
 *
 */
public interface Repositorable <T>{
	List<T> getAll();
	T get(int i);
	T get(Predicate<T> p);
	void create(T elem);
	void delete(int i);
	void update(T elem);
}
