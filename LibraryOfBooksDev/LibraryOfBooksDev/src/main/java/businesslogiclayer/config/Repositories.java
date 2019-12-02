package businesslogiclayer.config;

import dataaccesslayer.Repositorable;
import dataaccesslayer.omdb.AccountingRecordRepositoryOM;
import dataaccesslayer.omdb.BookRepositoryOM;
import dataaccesslayer.omdb.ClientRepositoryOM;
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
