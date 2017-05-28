package libary.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import libary.api.model.LibaryCard;
import libary.service.dao.LibaryCardDAO;


public class LibaryCardDAOJSON implements LibaryCardDAO {

	private static Logger LOG = LogManager.getLogger(LibaryCardDAOJSON.class);

	private final File database;

	public LibaryCardDAOJSON(String database) {
		super();
		this.database = new File(database);
	}

	public void createLibaryCard(LibaryCard libarycard) {
		Collection<LibaryCard> libarycards = new ArrayList<LibaryCard>(readLibaryCards());
		libarycards.add(libarycard);

		LibaryCard[] libarycardsArray = libarycards.toArray(new LibaryCard[libarycards.size()]);
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(LibaryCard.class, LibaryCardMixIn.class);
		try {
			mapper.writeValue(database, libarycardsArray);
		} catch (JsonGenerationException e) {
		
			e.printStackTrace();
		} catch (JsonMappingException e) {
		
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Collection<LibaryCard> readLibaryCards() {
			
		Collection<LibaryCard> result;
		LibaryCard[] libarycards = new LibaryCard[] {};
		try {
			ObjectMapper mapper = new ObjectMapper();
			libarycards = mapper.readValue(database, LibaryCard[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			LOG.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		result = new ArrayList<LibaryCard>(Arrays.asList(libarycards));
		return result;
	}

	private abstract class LibaryCardMixIn{
		@JsonIgnore abstract boolean openLibaryCard(); 
	}
}
