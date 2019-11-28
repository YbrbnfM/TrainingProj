package pl;


import lombok.Getter;
import pl.console.views.*;
import utility.Triple;

@SuppressWarnings("rawtypes")
public enum ViewEnum {
	MAIN_MENU(new Triple<Integer,String,Class>(0,"Главное меню",MainMenuView.class)),
	EDIT(new Triple<Integer,String,Class>(1,"Редактирование данных", EditView.class)),
	LIST_OF_BOOKS(new Triple<Integer,String,Class>(2,"Список книг", ListBookView.class)),
	SEARCHING_BOOKS(new Triple<Integer,String,Class>(3,"Поиск книги", SearchingBookView.class)),
	ACCOUNTING_RECORD(new Triple<Integer,String,Class>(4,"Выдача книги", AccountingRecordView.class));
	@Getter
	private final Triple<Integer, String, Class> value;	
	ViewEnum(Triple<Integer, String,Class> value) {
		this.value = value;
	}
}
