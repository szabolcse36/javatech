package libary.api.service;

import java.util.Collection;

import libary.api.model.Book;

public interface BookManagementService {

	Collection<Book> listBooks();
	
	void acquireBook(Book book);
	
}
