package libary.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import libary.api.model.Book;
import libary.service.dao.BookDAO;

public class BookDAOJSON implements BookDAO {

	private Logger LOGGER = LogManager.getLogger(BookDAOJSON.class);
	
	private File database;
	
	public BookDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Libary Databse : %s", database.getAbsolutePath()));
	}

	public void createBook(Book book) {
		Collection<Book> allBooks = readBooks();
		allBooks.add(book);
		Book[] extendedDatabase = allBooks.toArray(new Book[allBooks.size()]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Book (%s) has been added!", book.getIsbn()));
		

	}

	public Collection<Book> readBooks() {
		ObjectMapper mapper = new ObjectMapper();
		Book[] books = new Book[] {};
		try {
			books = mapper.readValue(database, Book[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		Collection<Book> result = new ArrayList<Book>(Arrays.asList(books));
		return result;
		
	}

	

}
