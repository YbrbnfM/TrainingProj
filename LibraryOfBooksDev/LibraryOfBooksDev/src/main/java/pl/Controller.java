package pl;

import java.util.List;
import java.util.function.Predicate;
import lombok.NonNull;

public interface Controller<T> {
	public T get(int id);

	public List<T> get(@NonNull Predicate<?> p);

	public void put(@NonNull T t);

	public void put(@NonNull String t);

	public void post(@NonNull T t);

	public void post(@NonNull String t);

	public void delete(int id);
}
