package softarch.portal.db;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;
import softarch.portal.db.IDatabaseFacade;

import java.util.List;
import java.util.Date;


public class DatabaseFacade implements IDatabaseFacade {

	/**
	 * Creates a new database facade.
	 */
	public DatabaseFacade(){
		//TODO Hier moeten we dus kijken naar de config file?
	}

	/**
	 * Inserts a new user profile into the user database.
	 */
	public void insert(UserProfile profile) throws DatabaseException
	 {
	
		// Perform the insert.
		
	}

	/**
	 * Updates an existing user profile in the user database.
	 */
	public void update(UserProfile profile)  throws DatabaseException
	{
		// Perform the update
		
	}

	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username) throws DatabaseException
	{
		// Perform select
		return null;	
	}

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username) throws DatabaseException
	{

		// TODO
		return false;
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List findRecords(String informationType, String queryString) throws DatabaseException
	{

		// Todo
		return null;
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date) throws DatabaseException
	{

		return null;
	}

	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd) throws DatabaseException
	{
	
		// Perform query
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType) throws DatabaseException
	{

		// TODO
		return -1;
	}

	/**
	 * Returns a list of all raw data.
	 */
	public List getRawData() throws DatabaseException
	{

		// TODO
		return null;
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id) throws DatabaseException
	{
		
		// Todo
		return null;
	}

	public void addRawData(RegularData rd) throws DatabaseException
	{
		// Todo
		
	}

	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd) throws DatabaseException
	{

		// TODO 
	}

	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd) throws DatabaseException
	{

		// TODO 
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords() throws DatabaseException
	{

		return -1;
	}
}
