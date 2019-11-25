package pl.models;

import java.util.List;
import pl.Model;

public class ListBookModel implements Model {
	List<BookModel> lstBook;

	public ListBookModel(List<BookModel> lst) {
		lstBook = lst;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(int i = 0;i<lstBook.size();i++)
			res.append(lstBook.get(i).toString()+"\n");
		//lstBook.stream().peek(x->res.append(x.toString()+"\n")).count();
		return res.toString();
	}
}
