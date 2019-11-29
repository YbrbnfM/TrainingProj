package pl.console.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import entities.Editable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.Controller;
import pl.EditableEntities;
import pl.EditingCommands;
import pl.controllers.AccountingRecordController;
import pl.controllers.BookController;
import pl.controllers.ClientController;

/*
 * Editing/Creating
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditView extends ConsoleView {
	Controller<? extends Editable> con;

	@Override
	public void onLoad() {
		super.onLoad();
		messages.add("Режим редактирования. Доступные сущности для редактирования: клиенты, книги и учетные записи"
				+ "\nПри заполнении данных нужные поля прописывать ввиде имя:значение (имя1:значение1 имя2:значение2)");
		messages.addAll(Arrays.asList(EditableEntities.values()).stream().map(x -> x.getValue().toString())
				.collect(Collectors.toList()));
	}

	@Override
	public void onRun() {
		mes = in.nextLine();
		if (con == null)
			try {
				int keyCommand = Integer.parseInt(mes);
				messages = new ArrayList<>();
				switch (EditableEntities.getByKey(keyCommand)) {
				case CLIENTS:
					con = new ClientController();
					System.out.println("Пример создания: firstname:example lastname:example1");
					break;
				case BOOKS:
					con = new BookController();
					System.out.println(
							"Пример создания: name:example category:id--example,description--example authors:firstname--example,lastname--example;...");
					break;
				case ACCOUNTING_RECORDS:
					con = new AccountingRecordController();
					break;
				default:
					super.onRun();
					return;
				}
				messages = Arrays.asList(EditingCommands.values()).stream().map(x -> x.getValue().toString())
						.collect(Collectors.toList());
			} catch (NoSuchElementException | IllegalArgumentException e) {
				super.onRun();
			}
		else {
			try {
				int keyCommand = Integer.parseInt(mes);
				switch (EditingCommands.getByKey(keyCommand)) {
				case CREATE:
					if (con.getClass() == AccountingRecordController.class)
						System.out.println("Для работы с учетными записями используйте представление \"Выдача книг\""
								+ "\n В текущем возможно только удалить(закрыть учетную запись)");
					else
						con.post(in.nextLine());
					break;
				case EDIT:
					if (con.getClass() == AccountingRecordController.class)
						System.out.println("Для работы с учетными записями используйте представление \"Выдача книг\""
								+ "\n В текущем возможно только удалить(закрыть учетную запись)");
					else
						con.put(in.nextLine());
					break;
				case DELETE:
					con.delete(Integer.parseInt(in.nextLine()));
					break;
				default:
					super.onRun();
					return;
				}
			} catch (IllegalArgumentException e) {
				super.onRun();
			}
		}
	}
}
