package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import customAnnotations.marker.IsReplicate;
import entities.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode (callSuper = true)
public class Client extends Account {	
	@NonNull
	List<AccountingRecord> accountingRecords = new ArrayList<>();
	public Client(int id, @NonNull String firstname, @NonNull String lastname, @NonNull List<AccountingRecord> accountingRecords) {
		super(id,firstname,lastname);
		setAccountingRecords(accountingRecords);		
	}
	@IsReplicate
	public Client(@NonNull Client c) {
		super(c);
		this.accountingRecords = c.accountingRecords.stream().map(x->new AccountingRecord(x)).collect(Collectors.toList());
	}
}
