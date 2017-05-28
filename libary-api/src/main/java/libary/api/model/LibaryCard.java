package libary.api.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import libary.api.model.User;

public class LibaryCard {

	private Book book;
	private User user;
	private boolean warning;
	

	public LibaryCard() {
		super();
	}

	public LibaryCard(Book book, User user) {
		super();
		this.book = book;
		this.user = user;
	
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public boolean openLibaryCard() throws ParseException {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date end_of_date = format.parse(user.getEnd_of_rental());
		return !warning && end_of_date.before(new Date());
		
		
	}
	
	
	@Override
	public String toString() {
		return "LibaryCard [book=" + book + ",user="  +user +"]";
	}

}
