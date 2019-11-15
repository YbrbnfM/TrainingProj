package entities;

import java.util.Date;

import customAnnotations.marker.IsReplicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountingRecord {
	@Setter(AccessLevel.PRIVATE)
	int id;
	@Setter(AccessLevel.PRIVATE)
	int accountId;
	@NonNull
	Book book;
	@NonNull
	Date receiptDate;
	@NonNull
	Date returnDate;
	@NonNull
	String statusId;
	@IsReplicate
	public AccountingRecord(AccountingRecord ar) {
		this.id = ar.id;
		this.accountId = ar.accountId;
		this.book = new Book(ar.book);
		this.receiptDate = (Date)ar.receiptDate.clone();
		this.returnDate = (Date)ar.returnDate.clone();
		this.statusId = ar.statusId;
		
	}
}
