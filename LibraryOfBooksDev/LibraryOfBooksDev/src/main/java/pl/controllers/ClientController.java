package pl.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.maven.plugin.descriptor.DuplicateParameterException;
import bll.AccountService;
import entities.Client;
import lombok.NonNull;
import pl.Controller;

public class ClientController implements Controller<Client> {
	AccountService as = new AccountService();

	@Override
	public Client get(int id) throws NoSuchElementException {
		try {
			return as.get(id);
		} catch (Exception e) {
			throw new NoSuchElementException("Not found");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> get(@NonNull Predicate<?> p) throws IllegalArgumentException {
		try {
			return as.get((Predicate<Client>) p);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid filter");
		}
	}

	@Override
	public void put(@NonNull Client t) throws IllegalArgumentException {
		as.update(t);
	}

	@Override
	public void post(@NonNull Client t) throws IllegalArgumentException {
		try {
			as.create(t);
		} catch (IllegalArgumentException | DuplicateParameterException e) {
			throw new IllegalArgumentException(
					"Некорректные входные данные, возможно такой пользователь уже существует");
		}
	}

	@Override
	public void delete(int id) {
		try {
			as.delete(id);
		} catch (NoSuchElementException e) {
			// Ничего не делать
		}
		
	}

	@Override
	public void put(@NonNull String t) throws IllegalArgumentException {
		List<String[]> args = Arrays.asList(t.split(" ")).stream().map(x -> x.split(":")).collect(Collectors.toList());
		if (args.size() != 3 || args.stream().anyMatch(x -> x.length != 2))
			throw new IllegalArgumentException("Некорректные воходные данные");
		try {
			int id = Integer.parseInt(args
					.remove(args.indexOf(args.stream().filter(x -> x[0].equalsIgnoreCase("id")).findAny().get()))[1]);
			String fn = "";
			String ln = "";
			for (int i = 0; i < args.size(); i++) {
				if (!args.get(i)[1].matches("[A-Za-zА-Яа-я]+"))
					throw new IllegalArgumentException("Некорректные входные данные");
				if (args.get(i)[0].equalsIgnoreCase("firstname")) {
					fn = args.get(i)[1];
					continue;
				}
				if (args.get(i)[0].equalsIgnoreCase("lastname")) {
					ln = args.get(i)[1];
					continue;
				}
				throw new IllegalArgumentException("Некорректные входные данные");
			}
			as.update(new Client(id, fn, ln, new ArrayList<>()));
		} catch (NoSuchElementException | NumberFormatException e) {
			throw new IllegalArgumentException("Некорректные воходные данные");
		}
	}

	@Override
	public void post(@NonNull String t) throws IllegalArgumentException {
		Client c = new Client();
		String[] args = t.split(" ");
		if (args.length != 2)
			throw new IllegalArgumentException("Некорректные входные данные");
		for (int i = 0; i < args.length; i++) {
			if (args[i].matches("[A-Za-zА-Яа-я]+:[A-Za-zА-Яа-я]+")) {
				String[] tmp = args[i].split(":");
				if (tmp[0].equalsIgnoreCase("firstname")) {
					c.setFirstname(tmp[1]);
					continue;
				}
				if (tmp[0].equalsIgnoreCase("lastname")) {
					c.setLastname(tmp[1]);
					continue;
				}
			}
			throw new IllegalArgumentException("Некорректные входные данные");
		}
		try {
			as.create(c);
		} catch (DuplicateParameterException e) {
			throw new IllegalArgumentException("Клиент с таким именем существует в системе");
		}
	}

}
