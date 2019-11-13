package entities;

import java.util.Date;

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
	int accountId;
	@NonNull
	Book book;
	@NonNull
	Date receiptDate;
	@NonNull
	Date returnDate;
	@NonNull
	String statusId;
}
