package bll.config;

import dal.Repositorable;
import dal.omdb.models.AccountingRecordRepositoryOM;
import dal.omdb.models.BookRepositoryOM;
import dal.omdb.models.ClientRepositoryOM;
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
