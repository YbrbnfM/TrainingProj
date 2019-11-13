package entities;

import java.util.ArrayList;
import java.util.List;
import entities.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client extends Account {	
	@NonNull
	List<AccountingRecord> accountingRecords = new ArrayList<>();
	public Client(int id, String firstname, String lastname, List<AccountingRecord> accountingRecords) {
		super(id,firstname,lastname);
		setAccountingRecords(accountingRecords);		
	}
}
