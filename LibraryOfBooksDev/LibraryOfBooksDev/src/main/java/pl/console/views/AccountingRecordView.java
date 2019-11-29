package pl.console.views;

import pl.controllers.AccountingRecordController;

/*
 * Выдача книг
 */
public class AccountingRecordView extends ConsoleView {
	AccountingRecordController arc = new AccountingRecordController();
	@Override
	public void onLoad() {
		super.onLoad();
		// new Date(2010, 12, 21);
		messages.add("Выдача книги(Создание учетной записи)\n"
				+ "форма создания: id_client:someid id_book:someid receiptDate:yyyy-mm-dd returnDate:yyyy-mm-dd");
	}

	@Override
	public void onRun() {
		mes = in.nextLine();
		try {
			arc.post(mes);
		} catch (Exception e) {
			super.onRun();
		}
	}

}
