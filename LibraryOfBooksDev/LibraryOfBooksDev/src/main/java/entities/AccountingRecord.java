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
public class AccountingRecord implements Editable {
	@Setter(AccessLevel.PRIVATE)
	int id;
	int accountId;
	int bookId;
	@NonNull
	Date receiptDate;
	@NonNull
	Date returnDate;
	@NonNull
	StatusAR statusId = StatusAR.OPENED;

	@IsReplicate
	public AccountingRecord(AccountingRecord ar) {
		this.id = ar.id;
		this.accountId = ar.accountId;
		this.bookId = ar.bookId;
		this.receiptDate = (Date) ar.receiptDate.clone();
		this.returnDate = (Date) ar.returnDate.clone();
		this.statusId = ar.statusId;
	}

	@Override
	public String toString() {
		return new String("" + id + "\t" + "accountId: " + accountId + "\t" + "bookId: " + bookId + "\t"
				+ "receiptDate: " + receiptDate.toString() + "\t" + "returnDate: " + returnDate.toString() + "\t"
				+ "statusId: " + statusId.getValue());
	}
	
	public StatusAR getStatusId() {
		if(this.returnDate.getTime() <= new Date().getTime())
			statusId = StatusAR.CLOSED;
		return this.statusId;
	}
}
