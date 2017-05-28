package libary.api.model;

public class Book {

	
	private String isbn;
	private String title;
	private String writer;
	private String genre;
	private int numberofpages;

	public Book() {
		super();
	}

	public Book(String isbn, String title, String writer, String genre, int numberofpages) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.writer = writer;
		this.genre = genre;
		this.numberofpages = numberofpages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNumberofpages() {
		return numberofpages;
	}

	public void setNumberofpages(int numberofpages) {
		this.numberofpages = numberofpages;
	}

	

}
