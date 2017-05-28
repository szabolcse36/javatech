package libary.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libary.api.model.LibaryCard;
import libary.api.model.Book;
import libary.api.model.User;
import libary.api.service.LibaryCardManagementService;
import libary.api.service.BookManagementService;
import libary.persist.LibaryCardDAOJSON;
import libary.persist.BookDAOJSON;
import libary.service.dao.LibaryCardDAO;
import libary.service.dao.BookDAO;
import libary.service.impl.LibaryCardManagementServiceImpl;
import libary.service.impl.BookManagementServiceImpl;


public class App {
	private static Logger LOG = LogManager.getLogger(LibaryCardDAOJSON.class);
	private static BookManagementService bookManager;
	private static LibaryCardManagementService 		libarycardManager;

	static {
		BookDAO bookDAO = new BookDAOJSON("resources/books.json");
		LibaryCardDAO libarycardDAO = new LibaryCardDAOJSON("resources/libarycards.json");
		bookManager = new BookManagementServiceImpl(bookDAO);
		libarycardManager = new LibaryCardManagementServiceImpl(libarycardDAO, bookDAO);
	}

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while (run) {

			String line = br.readLine();
			
				if(line.equals("list books")) {
				   listBooks();
				   
				}else if(line.equals("add book")) {
				  addBook();
				  
				}else if(line.equals("list cards" )) {
					printLibaryCard(libarycardManager.listLibaryCards());
					
				}else if(line.equals("add card")) {
					  addLibaryCard();
					  
				}else if(line.equals("list open cards")) {
					printLibaryCard(libarycardManager.listOpenLibaryCards());
					
				}else if(line.equals("exit")) {
					System.out.println("Program has been terminated");
					break;
					
				}else if(line.equals("?")) {
				
					System.out.println("list books ; add book ; list cards ; add card ; list open cards");	
					
					}else {
				   System.out.println("Bad command! Please try again! Exit -> 'exit' Help -> '?'");
				   LOG.warn((String.format("User error (bad command)")));
				}
				}
				}

	private static void listBooks() {
		final int tableWidth = 101;
		printHorisontalLine(tableWidth);
		System.out.println("|       ISBN        |       Title       |       Writer      |       Genre       |  Number of pages  |");
		printHorisontalLine(tableWidth);
		for (Book book : bookManager.listBooks()) {
			System.out.println(String.format("| %1$17s | %2$17s | %3$17s | %4$17s | %5$17d |", book.getIsbn(),
					book.getTitle(), book.getWriter(), book.getGenre(), book.getNumberofpages()));
			printHorisontalLine(tableWidth);
			LOG.info(String.format("Books listed"));
		}
	}
	
	private static void printHorisontalLine(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static void addBook() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ISBN.: ");
		String isbn = br.readLine();
		System.out.println("Title: ");
		String title = br.readLine();
		System.out.println("Writer: ");
		String writer = br.readLine();
		System.out.println("Genre: ");
		String genre = br.readLine();
		System.out.println("Number of pages: ");
		int numberofpages = Integer.parseInt(br.readLine());
		Book book = new Book(isbn, title, writer, genre, numberofpages);
		bookManager.acquireBook(book);

	}

	private static void printLibaryCard(Collection<LibaryCard> libarycards) throws ParseException {
		final int tableWidth = 146;
		printHorisontalLine(tableWidth);
		System.out.println(
				"|                              Könyv                              |                                      Olvasó                                  |");
		printHorisontalLine(tableWidth);
		System.out.println(
				"| ISBN |    Cím    |   Író   |   Műfaj   | Old. száma |    Név    |  Id  | Kölcsönzés kezd. | Kölcsönzés vége | Köl. nap | 135/nap | Felszólítás |");
		printHorisontalLine(tableWidth);
		for (LibaryCard libarycard : libarycards) {
			Book book = libarycard.getBook();
			User user = libarycard.getUser();
			
			System.out.println(String.format(
					"| %1$4s | %2$9s | %3$7s | %4$9s | %5$10d | %6$9s | %7$4s | %8$16s | %9$15s | %10$8s | %11$7s | %12$7s |",
					book.getIsbn(), book.getTitle(), book.getWriter(), book.getGenre(), book.getNumberofpages(),user.getName(),user.getId(),user.getStart_of_rental(),
					user.getEnd_of_rental(),user.getNumber_of_days_paid(),user.getPay(),libarycard.openLibaryCard()));
		}
		LOG.info(String.format("Cards listed"));
	}

	private static void addLibaryCard() throws IOException, ParseException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ISBN: ");
		String isbn = br.readLine();
		System.out.println("Title: ");
		String title = br.readLine();
		System.out.println("Writer: ");
		String writer = br.readLine();
		System.out.println("Genre: ");
		String genre = br.readLine();
		System.out.println("Number of pages: ");
		int numberofpages = Integer.parseInt(br.readLine());
		System.out.println("User name: ");
		String name = br.readLine();
		System.out.println("User id: ");
		int id = Integer.parseInt(br.readLine());
		
		Book b = new Book(isbn, title, writer, genre, numberofpages);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date cur_date = Calendar.getInstance().getTime();
		LOG.info(String.format("DateFormat pass (%s)", cur_date));
		String start_of_rental = df.format(cur_date);
		
		System.out.println("End of rental (yyyy-MM-dd): ");
		String end_of_rental = br.readLine();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date end_of_date = format.parse(end_of_rental);
		LOG.info(String.format("end_of_rental pass" ,end_of_rental));
						
		int oneDay = 24 * 3600 * 1000;
		long number_of_days_paid = ((end_of_date.getTime()-cur_date.getTime())/oneDay);
		long pay= (number_of_days_paid*135) ;
		
		User u = new User(id,name,start_of_rental, end_of_rental,number_of_days_paid,pay);
		LOG.info("Create sucess!");
		libarycardManager.lcard(b,u);
		
	
}}
