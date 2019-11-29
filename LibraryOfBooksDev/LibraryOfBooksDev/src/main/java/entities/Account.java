package entities;

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
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Account {
	@Setter(AccessLevel.PRIVATE)
	int id;
	@NonNull
	String firstname;
	@NonNull
	String lastname;

	@IsReplicate
	public Account(@NonNull Account a) {
		this.firstname = a.firstname;
		this.id = a.id;
		this.lastname = a.lastname;
	}
}
