package pl.models;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.Model;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class DefaultModel implements Model {
	List<String> strs = new ArrayList<>();

	public final List<String> getStrs() {
		return strs;
	}

	public final void setStrs(List<String> strs) {
		this.strs = strs;
	}

	@Override
	public String toString() {
		return new String(strs.stream().reduce("",(a,b)->a+"\n"+b));
	}
	
}
