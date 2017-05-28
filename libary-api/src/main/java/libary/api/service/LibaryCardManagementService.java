package libary.api.service;

import java.text.ParseException;
import java.util.Collection;
import libary.api.model.LibaryCard;
import libary.api.model.*;



public interface LibaryCardManagementService {

	Collection<LibaryCard> listLibaryCards();
	Collection<LibaryCard> listOpenLibaryCards() throws ParseException;
	
	void lcard(Book book,User user);
}
