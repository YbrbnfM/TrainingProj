package pl;

import java.util.function.Predicate;

public interface Controller<T extends Model> {
	public T get(int id);
	public T get(Predicate<?> p);
	public void put(T t);
	public void post(T t);
	public void delete(int id);
}
