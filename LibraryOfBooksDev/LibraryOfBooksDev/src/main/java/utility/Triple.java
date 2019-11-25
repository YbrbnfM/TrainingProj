package utility;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class Triple<K,V,V2> {
	@Getter
	final K key;
	@Getter
	@Setter
	V value;
	@Setter
	@Getter
	V2 value2;
	public Triple(K key, V value, V2 value2) {
		this.key = key;
		this.value = value;
		this.value2 = value2;
	}
	
	@Override
	public String toString() {
		return new String(""+key+"\t:\t"+value);
	}
	
	

}
