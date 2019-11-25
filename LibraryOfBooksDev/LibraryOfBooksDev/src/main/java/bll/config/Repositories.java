package bll.config;

import dal.Repositorable;
import dal.omdb.AccountingRecordRepositoryOM;
import dal.omdb.BookRepositoryOM;
import dal.omdb.ClientRepositoryOM;
import lombok.Getter;

public enum Repositories{
	CLIENT_REPOSITORY(new ClientRepositoryOM()),
	ACCOUNTING_RECORD_REPOSITORY(new AccountingRecordRepositoryOM()),
	BOOK_REPOSITOTY(new BookRepositoryOM());
	@Getter
	private Repositorable<?> value;
	
	Repositories(Repositorable<?> r) {
		this.value = r;
	}
}
