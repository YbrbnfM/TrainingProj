package testingConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.*;

public class TestingConsole {

	public static void main(String[] args) {
		Book b0 = new Book();
		Author a0 = new Author();
		a0.setFirstname("A0");
		a0.setLastname("0a");
		Author a1 = new Author();
		a1.setFirstname("A0");
		a1.setLastname("0a");
		b0.setAuthors(Arrays.asList(a0, a1));
	    
	    System.out.print(b0.getAuthors().get(0).getFirstname());

	}
}
