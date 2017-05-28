package libary.service.dao;

import java.util.Collection;

import libary.api.model.Book;

public interface BookDAO {

	void createBook(Book book);
	
	Collection<Book> readBooks();

	
	
	
}
