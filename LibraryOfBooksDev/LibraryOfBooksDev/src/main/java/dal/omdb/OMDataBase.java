package dal.omdb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import customAnnotations.marker.IsReplicate;
import entities.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OMDataBase {
	static int idObject = 1;
	@Getter
	List<Account> accounts = new ArrayList<>();
	@Getter
	List<Author> authors = new ArrayList<>();
	@Getter
	List<Client> clients = new ArrayList<>();
	@Getter
	List<AccountingRecord> accountingRecords = new ArrayList<>();
	@Getter
	List<Book> books = new ArrayList<>();

	static final OMDataBase instance = new OMDataBase();

	public static OMDataBase getInstance() {
		return instance;
	}

	private <T> void generateId(T o) {
		try {
			o.getClass().getDeclaredField("id").setInt(o, idObject++);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Not found or not access to the field \"id\"");
		}
	}

	public void addAccount(@NonNull Account a) {
		generateId(a);
		if (a.getClass().equals(Author.class)) {
			authors.add(new Author((Author) a));
			return;
		}
		if (a.getClass().equals(Client.class)) {
			clients.add(new Client((Client) a));
			return;

		}
		throw new IllegalArgumentException("Недопустимый тип объекта a");
	}

	public void addBook(@NonNull Book b) {
		generateId(b);
		books.add(new Book(b));
	}

	public void addAccountingRecord(@NonNull AccountingRecord ar) {
		generateId(ar);
		accountingRecords.add(new AccountingRecord(ar));
	}

	// если объект по какойто причине был удален во время редактирования - он
	// восстанавливается в измененном виде
	// !!! Целесообразнанно ли писать такие методы для сокращения кода, используя
	// рефлексию
	@SuppressWarnings("unchecked")
	private <T> void update(T o, List<T> lst) {
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

//	private <T> void update(T o) {
//		
//	}

//	public void update(@NonNull Author a) {
//		Optional<Author> tmp = authors.stream().filter(x -> x.getId() == a.getId()).findAny();
//		if (tmp.isPresent())
//			authors.set(authors.indexOf(tmp.get()), new Author(a));
//		else // !!
//			authors.add(new Author(a));
//	}

	public void update(@NonNull Author a) throws IllegalArgumentException {
		this.update(a, authors);
	}

	public void update(@NonNull Client c) throws IllegalArgumentException {
		this.update(c, clients);
	}

	public void update(@NonNull Book b) throws IllegalArgumentException {
		this.update(b, books);
	}

	public void update(@NonNull AccountingRecord ar) throws IllegalArgumentException {
		this.update(ar, accountingRecords);
	}

//	public void deleteAuthor(int id) {
//		// To do
//		throw new RuntimeException("Отсутствует реализация");
//	}
//
//	public void deleteAuthor(Predicate<Author> p) {
//		// To do
//		throw new RuntimeException("Отсутствует реализация");
//	}
	// удалять ли учетные записи после удаления клиента?
	public boolean deleteClient(int id) {
		Optional<Client> tmpcOptional = clients.stream().filter(x -> x.getId() == id).findAny();
		if (tmpcOptional.isPresent()) {
			accountingRecords.removeAll(tmpcOptional.get().getAccountingRecords());
			return clients.remove(tmpcOptional.get());
		} 
		return false;
//		else
//			throw new IllegalArgumentException("Current arg \"id\" not founded");
	}
	
	public boolean deleteClients(Predicate<Client> p) {
		int psize = clients.size();
		clients.stream().filter(p).forEach(x->deleteClient(x.getId()));
		if(psize !=clients.size())
			return true;
		return false;
	}

	public boolean deleteAccountingRecord(int id) {
		Optional<AccountingRecord> tmpOptional=accountingRecords.stream().filter(x->x.getId()==id).findAny();
		if(tmpOptional.isPresent())
			return accountingRecords.remove(tmpOptional.get());
		return false;
	}

	public boolean deleteAccountingRecords(Predicate<AccountingRecord> p) {
		return accountingRecords.removeAll(accountingRecords.stream().filter(p).collect(Collectors.toList()));
	}

	public boolean deleteBook(int id) {
		Optional<Book> tmpOptional=books.stream().filter(x->x.getId()==id).findAny();
		if(tmpOptional.isPresent())
			return books.remove(tmpOptional.get());
		return false;
	}

	public boolean deleteBooks(Predicate<Book> p) {
		return books.removeAll(books.stream().filter(p).collect(Collectors.toList()));
	}

	

	public Author getAuthor(int id) {
		return authors.stream().filter(x->x.getId()==id).findAny().get();
	}

	public List<Author> getAuthors(Predicate<Author> p) {
		return authors.stream().filter(p).collect(Collectors.toList());
	}

	public Client getClient(int id) {
		return clients.stream().filter(x->x.getId()==id).findAny().get();
	}

	public List<Client> getClients(Predicate<Client> p) {
		return clients.stream().filter(p).collect(Collectors.toList());
	}

	public AccountingRecord getAccountingRecord(int id) {
		return accountingRecords.stream().filter(x->x.getId()==id).findAny().get();
	}

	public List<AccountingRecord> getAccountingRecords(Predicate<AccountingRecord> p) {
		return accountingRecords.stream().filter(p).collect(Collectors.toList());
	}

	public Book getBook(int id) {
		return books.stream().filter(x->x.getId()==id).findAny().get();
	}

	public List<Book> getBooks(Predicate<Book> p) {
		return books.stream().filter(p).collect(Collectors.toList());
	}
}
//!!! необходимо ли возвращать копии объектов и придерживаться строгой инкапсуляции?