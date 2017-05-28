package libary.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import libary.api.model.LibaryCard;
import libary.api.model.Book;
import libary.api.model.User;
import libary.api.service.LibaryCardManagementService;
import libary.service.dao.LibaryCardDAO;
import libary.service.dao.BookDAO;


public class LibaryCardManagementServiceImpl implements LibaryCardManagementService {
	
	private static Logger LOG = LogManager.getLogger(LibaryCardManagementServiceImpl.class);
	
	private LibaryCardDAO libarycardDAO;
	private BookDAO bookDAO;
	
	public LibaryCardManagementServiceImpl(LibaryCardDAO libarycardDAO, BookDAO bookDAO) {
		super();
		this.libarycardDAO = libarycardDAO;
		this.bookDAO = bookDAO;
	}

	public Collection<LibaryCard> listLibaryCards() {
		return libarycardDAO.readLibaryCards();
	}

	public Collection<LibaryCard> listOpenLibaryCards() throws ParseException {
		Collection<LibaryCard> result = new ArrayList<LibaryCard>();
		for(LibaryCard libarycard : libarycardDAO.readLibaryCards()){
			if(libarycard.openLibaryCard()){
				result.add(libarycard);
			}
		}
		return result;
	}

	public void lcard(Book book, User user) {
		LibaryCard libarycard = new LibaryCard(book,  user);
		libarycardDAO.createLibaryCard(libarycard);
		LOG.info(String.format("LibaryCard name: (%s) has been added!", user.getName()));
	}

}
