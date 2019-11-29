package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import customAnnotations.marker.IsReplicate;
import entities.Account;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Author extends Account {
	public Author(int id, @NonNull String firstname, @NonNull String lastname) {
		super(id, firstname, lastname);
	}

	@IsReplicate
	public Author(@NonNull Author a) {
		super(a);
	}

	@Override
	public String toString() {
		return new String("id: " + id + "\t" + firstname + " " + lastname);
	}
}
