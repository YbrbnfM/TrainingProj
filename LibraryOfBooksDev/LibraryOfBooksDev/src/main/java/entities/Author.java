package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import entities.Account;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends Account {
	public Author(int id, String firstname, String lastname) {
		super(id,firstname,lastname);
	}
}
