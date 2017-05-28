package libary.service.impl;

import java.util.Collection;

import libary.api.model.Book;
import libary.api.service.BookManagementService;
import libary.service.dao.BookDAO;


public class BookManagementServiceImpl implements BookManagementService {

	private BookDAO bookDAO;

	public BookManagementServiceImpl(BookDAO bookDAO) {
		super();
		this.bookDAO = bookDAO;
	}

	public Collection<Book> listBooks() {
		return bookDAO.readBooks();
	}

	public void acquireBook(Book book) {
		bookDAO.createBook(book);
	}
	
	

}
