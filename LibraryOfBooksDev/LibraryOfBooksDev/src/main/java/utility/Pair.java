package utility;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class Pair<K, V> {
	@Getter
	final K key;
	@Getter
	@Setter
	V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return new String("" + key + " : " + value);
	}
}
