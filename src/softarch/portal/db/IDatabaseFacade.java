package softarch.portal.db;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;


public interface IDatabaseFacade {
	
	public void insert(UserProfile profile) throws DatabaseException ;

	public void update(UserProfile profile) throws DatabaseException ;

	public UserProfile findUser(String username) throws DatabaseException ;

	public boolean userExists(String username) throws DatabaseException ;

	public List findRecords(String informationType, String queryString) throws DatabaseException ;

	public List findRecordsFrom(String informationType, Date date) throws DatabaseException ;

	public void add(RegularData rd) throws DatabaseException ;
	
	public int getNumberOfRegularRecords(String informationType) throws DatabaseException ;
	
	public List getRawData() throws DatabaseException, DatabaseException ;
		
	public RawData getRawData(int id) throws DatabaseException ;
	
	public void addRawData(RegularData rd) throws DatabaseException ;
		
	public void deleteRawData(RawData rd) throws DatabaseException ;
		
	public void updateRawData(RawData rd) throws DatabaseException ;
		
	public int getNumberOfRawRecords() throws DatabaseException ;
}
