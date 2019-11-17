package bll;

import dal.omdb.models.ClientRepositoryOM;

public class AccountService {
	ClientRepositoryOM cr = new ClientRepositoryOM();

	public AccountService(ClientRepositoryOM cr) {
		this.cr = cr;
	}
	
}
