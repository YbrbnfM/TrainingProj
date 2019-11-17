package testingConsole;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestingConsole {
	private static final Logger log = LogManager.getLogger();
	public static void main(String[] args) {
//		Book b0 = new Book();
//		Author a0 = new Author();
//		a0.setFirstname("A0");
//		a0.setLastname("0a");
//		Author a1 = new Author();
//		a1.setFirstname("A0");
//		a1.setLastname("0a");
//		b0.setAuthors(Arrays.asList(a0, a1));
		System.out.println("Begin");
		log.error("ErrorTest");
		log.info("Info");
		System.out.println("End");

	}
}
