package softarch.portal.db.webservice;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.axis.message.MessageElement;

import librarysearch.soft.*;
import softarch.portal.data.Book;

public class LibraryService {

	public ArrayList<Book> getBooks(String query)
	{
		try
		  {
			  LibrarySearchServiceLocator a = new LibrarySearchServiceLocator();
			  LibrarySearchSOAPBindingStub service = (LibrarySearchSOAPBindingStub) a.getLibrarySearchServicePort(new URL("http://localhost:8080/ode/processes/LibrarySearchService"));
			  LibrarySearchResponse response = service.process(new LibrarySearchRequest(""));
			  
			  BookList books = response.getBooks();
			  
			  MessageElement[] mes = books.get_any();
			  
			  return parseXML(mes);	  
		  }
		  catch (Exception e)
		  {
		   e.printStackTrace();
		  }
		
		// An error occurred, null.
		return null;
	}
	
	private ArrayList<Book> parseXML(MessageElement[] mes)
	{
		// Make the arraylist available for our list of books.
		ArrayList<Book> books = new ArrayList<Book>();
		
		// Get all the nodes under the root XML node.
		ArrayList<MessageElement> childNodes = (ArrayList<MessageElement>) mes[0].getChildren();
		
		// First get all of the SOFT books
		for(MessageElement currentNode : childNodes)
		{
			// CurrentNode = a book from the SOFT library
			List<MessageElement> currentBook = currentNode.getChildren();
			try {

				// Fill in all the fields of our book item.
				String author = currentBook.get(0).getValue();
				Date d = new Date(Integer.parseInt(currentBook.get(1).getValue()), 0, 0);
				long isbn = Long.parseLong(currentBook.get(2).getValue());
				String lang = currentBook.get(3).getValue();
				String publisher = currentBook.get(4).getValue();
				String title = currentBook.get(5).getValue();
				
				Book b = new Book(new Date(), author, isbn, -1, d, publisher, "N/A", "N/A", title);
				books.add(b);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		// Now get all the National Library books; these have a different date
		// Separate this loop, in case anything changes in the future for the NAT <-> SOFT libs.
		for(int ctr = 1; ctr < mes.length; ++ctr)
		{
			// CurrentNode = a book from the SOFT library
			List<MessageElement> currentBook = mes[ctr].getChildren();
			try {
				// Fill in all the fields of our book item.
				String author = currentBook.get(0).getValue();
				
				// Here, the date is in text format instead of only the year.
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				String s = currentBook.get(1).getValue();
				Date d = format.parse(s);
				
				long isbn = Long.parseLong(currentBook.get(2).getValue());
				String lang = currentBook.get(3).getValue();
				String publisher = currentBook.get(4).getValue();
				String title = currentBook.get(5).getValue();
				
				Book b = new Book(new Date(), author, isbn, -1, d, publisher, "N/A", "N/A", title);
				books.add(b);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return books;
	}
	
}
