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
}
