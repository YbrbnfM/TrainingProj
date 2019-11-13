package dal.omdb;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import entities.*;
import lombok.Getter;

public class OMDataBase {
	private static int idObject = 1;
	@Getter
	private List<Account> accounts = new ArrayList<>();
	@Getter
	private List<AccountingRecord> accountingRecords = new ArrayList<>();
	@Getter
	private List<Book> books = new ArrayList<>();
	
	private static final OMDataBase instance = new OMDataBase();
	private OMDataBase() {}
	public static OMDataBase getInstance() {
		return instance;
	}
	
	private int generateId() {
		return idObject++;
	}
	
	public void addAccount(Account a) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void addBook (Book b) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void addAccountingRecord(AccountingRecord ar) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	
	public void update(Account a) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void update(Book b) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void update(AccountingRecord ar) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	
	public void deleteAccount(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void deleteAccount(Predicate<Account> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void deleteAccountingRecord(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void deleteAccountingRecord(Predicate<AccountingRecord> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void deleteBook(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public void deleteBook(Predicate<Book> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	
	public Account getAccount(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public Account getAccount(Predicate<Account> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public Account getAccountingRecord(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public Account getAccountingRecord(Predicate<AccountingRecord> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public Account getBook(int id) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
	public Account getBook(Predicate<Book> p) {
		//To do
		throw new RuntimeException("Отсутствует реализация");
	}
}
//!!! необходимо ли возвращать копии объектов и придерживаться строгой инкапсуляции?
