package softarch.portal.db.webservice;

import java.util.ArrayList;
import java.util.List;

import softarch.portal.db.DatabaseException;

public class WebServiceFacade {

	public List findRecords(String informationType, String queryString)
			throws DatabaseException {

			/*if(informationType.equals("Books"))
			{*/
				LibraryService ls = new LibraryService();
				return ls.getBooks(queryString);
			/*}
			else
				return new ArrayList<>();*/
		}
	
}
