package dal.omdb.models;

import java.util.List;
import java.util.function.Predicate;
import dal.Repositorable;
import entities.Account;

public class AccountRepositoryOM implements Repositorable<Account> {

	@Override
	public List<Account> getAll() {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public Account get(int i) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public Account get(Predicate<Account> p) {
		// To do
		throw new RuntimeException("Отсутствует реализация");
	}

	@Override
	public void create(Account elem) {
		// To do
		throw new RuntimeException("Отсутствует реализация");

	}

	@Override
	public void delete(int i) {
		// To do
		throw new RuntimeException("Отсутствует реализация");

	}

	@Override
	public void update(Account elem) {
		// To do
		throw new RuntimeException("Отсутствует реализация");

	}

}
