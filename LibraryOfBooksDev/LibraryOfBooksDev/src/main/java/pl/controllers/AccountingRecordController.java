package pl.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.apache.maven.plugin.descriptor.DuplicateParameterException;

import bll.AccountService;
import entities.AccountingRecord;
import entities.StatusAR;
import lombok.NonNull;
import pl.Controller;

public class AccountingRecordController implements Controller<AccountingRecord> {

	AccountService as;

	@Override
	public AccountingRecord get(int id) throws NoSuchElementException {
		try {
			return as.getRecords(x -> x.getId() == id).get(0);
		} catch (Exception e) {
			throw new NoSuchElementException("Not found");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountingRecord> get(@NonNull Predicate<?> p) throws IllegalArgumentException {
		try {
			return as.getRecords((Predicate<AccountingRecord>) p);
		} catch (Exception e) {
			throw new NoSuchElementException("Invalid filter");
		}
	}

	@Override
	public void put(@NonNull AccountingRecord t) throws IllegalArgumentException {

		try {
			as.updateAccountRecord(t);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal sended data");
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void post(@NonNull AccountingRecord t) throws IllegalArgumentException {
		try {
			as.createAccountingRecord(t);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal sended data");
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws IllegalArgumentException, NoSuchElementException {
		AccountingRecord ar = this.get(id);
		ar.setStatusId(StatusAR.CLOSED);
		this.put(ar);
	}

	@Override
	public void put(@NonNull String t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void post(@NonNull String t) {
		// TODO Auto-generated method stub

	}

}
