package bll;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.maven.plugin.descriptor.DuplicateParameterException;
import bll.config.Repositories;
import dal.Repositorable;
import entities.Client;
import entities.StatusAR;
import entities.AccountingRecord;
import entities.Book;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountService {
	@SuppressWarnings("unchecked")
	Repositorable<Client> clientRepositorable = (Repositorable<Client>) Repositories.CLIENT_REPOSITORY.getValue();
	@SuppressWarnings("unchecked")
	Repositorable<AccountingRecord> arRepositorable = (Repositorable<AccountingRecord>) Repositories.ACCOUNTING_RECORD_REPOSITORY
			.getValue();
	@SuppressWarnings("unchecked")
	Repositorable<Book> bookRepositorable = (Repositorable<Book>) Repositories.BOOK_REPOSITOTY.getValue();

	public void create(@NonNull Client c) throws DuplicateParameterException {
		// clientRepositorable.create(c);
		if (clientRepositorable.get(x -> x.getFirstname() == c.getFirstname() && x.getLastname() == c.getLastname())
				.isEmpty())
			clientRepositorable.create(c);
		else
			throw new DuplicateParameterException("Client whith this names is already exist");
	}

	public void update(@NonNull Client c) throws IllegalArgumentException {
		if (c.getId() == 0)
			throw new IllegalArgumentException("ID can't be 0");
		try {
			clientRepositorable.update(c);
		} catch (NoSuchElementException e) {
			clientRepositorable.create(c);
		}
	}

	public void delete(int id) throws NoSuchElementException {
		clientRepositorable.delete(id);
		arRepositorable.delete(x -> x.getAccountId() == id);
	}

	public void delete(@NonNull Predicate<Client> p) throws NoSuchElementException {
		List<Client> deleted = clientRepositorable.delete(p);
		arRepositorable.delete(x -> deleted.stream().filter(y -> y.getId() == x.getAccountId()).count() != 0);
	}

	@NonNull
	public Client get(int id) throws NoSuchElementException {
		Client c = clientRepositorable.get(id);
		c.setAccountingRecords(arRepositorable.get(x -> x.getAccountId() == id));
		return c;
	}

	@NonNull
	public List<Client> get(Predicate<Client> p) {
		return clientRepositorable.get(p).stream()
				.peek(x -> x.setAccountingRecords(arRepositorable.get(y -> y.getAccountId() == x.getId())))
				.collect(Collectors.toList());
	}

	@NonNull
	public List<Client> getAll() {
		return clientRepositorable.get(x -> true).stream()
				.peek(x -> x.setAccountingRecords(arRepositorable.get(y -> y.getAccountId() == x.getId())))
				.collect(Collectors.toList());
	}

	public void createAccountingRecord(@NonNull AccountingRecord ar)
			throws IllegalArgumentException, DuplicateParameterException {
		Client c;
		Book b;
		try {
			c = clientRepositorable.get(ar.getAccountId());
			b = bookRepositorable.get(ar.getBookId());
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Such book or client does not exist");
		}
		if (ar.getReceiptDate().getTime() >= ar.getReturnDate().getTime()
				|| ar.getReturnDate().getTime() <= new Date().getTime())
			throw new IllegalArgumentException("now/receiptDate >= returnDate");
		if (!arRepositorable.get(
				x -> x.getAccountId() == c.getId() && x.getBookId() == b.getId() && x.getStatusId() == StatusAR.OPENED)
				.isEmpty())
			throw new DuplicateParameterException("Such accountRecord is already exist");
		if (arRepositorable.get(x -> x.getBookId() == b.getId()).stream()
				.filter(x -> x.getStatusId() == StatusAR.OPENED).findAny().isPresent())
			throw new IllegalArgumentException("This book is not free");
		arRepositorable.create(ar);
	}

	public List<AccountingRecord> getRecords(Predicate<AccountingRecord> p) throws NoSuchElementException {
		return arRepositorable.get(p);
	}

	public void updateAccountRecord(AccountingRecord ar) throws IllegalArgumentException, DuplicateParameterException {
		if (ar.getId() == 0)
			throw new IllegalArgumentException("ID can't be 0");
		Client c;
		Book b;
		try {
			c = clientRepositorable.get(ar.getAccountId());
			b = bookRepositorable.get(ar.getBookId());
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Such book or client does not exist");
		}
		if (ar.getReceiptDate().getTime() >= ar.getReturnDate().getTime()
				|| ar.getReturnDate().getTime() <= new Date().getTime())
			throw new IllegalArgumentException("now/receiptDate >= returnDate");
		if (!arRepositorable.get(
				x -> x.getAccountId() == c.getId() && x.getBookId() == b.getId() && x.getStatusId() == StatusAR.OPENED)
				.isEmpty())
			throw new DuplicateParameterException("Such accountRecord is already exist");
		if (arRepositorable.get(x -> x.getBookId() == b.getId()).stream()
				.filter(x -> x.getStatusId() == StatusAR.OPENED).findAny().isPresent())
			throw new IllegalArgumentException("This book is not free");
		try {
			arRepositorable.update(ar);
		} catch (NoSuchElementException e) {
			arRepositorable.create(ar);
		}
	}
}