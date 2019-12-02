package presentationlayer.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.maven.plugin.descriptor.DuplicateParameterException;

import businesslogiclayer.AccountService;
import entities.AccountingRecord;
import entities.StatusAR;
import lombok.NonNull;
import presentationlayer.Controller;

public class AccountingRecordController implements Controller<AccountingRecord> {

	AccountService as = new AccountService();

	@Override
	public AccountingRecord get(int id) throws NoSuchElementException {
		try {
			return as.getRecords(x -> x.getId() == id).get(0);
		} catch (Exception e) {
			throw new NoSuchElementException("Не найдено");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountingRecord> get(@NonNull Predicate<?> p) throws IllegalArgumentException {
		try {
			return as.getRecords((Predicate<AccountingRecord>) p);
		} catch (Exception e) {
			throw new NoSuchElementException("Неверный фильтр");
		}
	}

	@Override
	public void put(@NonNull AccountingRecord t) throws IllegalArgumentException {

		try {
			as.updateAccountRecord(t);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Некоорректные входные данные");
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException("Текущий экземпляр уже существует");
		}
	}

	@Override
	public void post(@NonNull AccountingRecord t) throws IllegalArgumentException {
		try {
			as.createAccountingRecord(t);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Некоорректные входные данные");
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException("Текущий экземпляр уже существует");
		}
	}

	@Override
	public void delete(int id) {
		try {
			AccountingRecord ar = this.get(id);
			ar.setStatusId(StatusAR.CLOSED);
			this.put(ar);

		} catch (NoSuchElementException e) {
			// Ничего не делать
		}
	}

	@Override
	public void put(@NonNull String t) {
		AccountingRecord ar = parseRequestString(t, 5);
		parseRequestString(t, 5);
		try {
			as.updateAccountRecord(ar);
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException("Текущий экземпляр уже существует");
		}
	}

	@Override
	public void post(@NonNull String t) throws IllegalArgumentException {
		AccountingRecord ar = parseRequestString(t, 4);
		;

		try {
			as.createAccountingRecord(ar);
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException("Текущий экземпляр уже существует");
		}
	}

	private AccountingRecord parseRequestString(@NonNull String s, int count) throws IllegalArgumentException {
		AccountingRecord ar = new AccountingRecord();
		String[] args = s.split(" ");
		if (args.length != count)
			throw new IllegalArgumentException("Некооректные входные данные");
		for (int i = 0; i < args.length; i++) {
			if (!args[i].matches("[\\w]+:[\\w-]+"))
				throw new IllegalArgumentException("Некооректные входные данные");
			String[] tmp = args[i].split(":");
			if (tmp[0].equalsIgnoreCase("id")) {
				ar = new AccountingRecord(Integer.parseInt(tmp[1]), ar.getAccountId(), ar.getBookId(),
						ar.getReceiptDate(), ar.getReturnDate(), StatusAR.OPENED);
				continue;
			}
			if (tmp[0].equalsIgnoreCase("id_client")) {
				ar.setAccountId(Integer.parseInt(tmp[1]));
				continue;
			}
			if (tmp[0].equalsIgnoreCase("id_book")) {
				ar.setBookId(Integer.parseInt(tmp[1]));
				continue;
			}
			if (tmp[0].equalsIgnoreCase("receiptDate")) {
				List<Integer> dateIntA = Arrays.asList(tmp[1].split("-")).stream().map(x -> Integer.parseInt(x))
						.collect(Collectors.toList());
				ar.setReceiptDate(new Date(dateIntA.get(0), dateIntA.get(1), dateIntA.get(2)));
				continue;
			}
			if (tmp[0].equalsIgnoreCase("returnDate")) {
				List<Integer> dateIntA = Arrays.asList(tmp[1].split("-")).stream().map(x -> Integer.parseInt(x))
						.collect(Collectors.toList());
				ar.setReturnDate(new Date(dateIntA.get(0), dateIntA.get(1), dateIntA.get(2)));
				continue;
			}
			throw new IllegalArgumentException("Некооректные входные данные");
		}
		return ar;
	}

}
