package libary.service.dao;

import java.util.Collection;

import libary.api.model.LibaryCard;

public interface LibaryCardDAO {

	void createLibaryCard(LibaryCard libarycard);

	Collection<LibaryCard> readLibaryCards();

}
