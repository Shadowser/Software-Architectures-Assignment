package softarch.portal.jsondb;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;

import java.util.List;
import java.util.Date;


public class DatabaseFacade implements IDatabaseFacade {

	private String dbPath = "";
	
	/**
	 * Creates a new database facade.
	 */
	public DatabaseFacade(String dbPath) {
		this.dbPath = dbPath;
	}

	/**
	 * Inserts a new user profile into the user database.
	 */
	public void insert(UserProfile profile)
		throws JsonException {
	
		// Perform the insert.
	}

	/**
	 * Updates an existing user profile in the user database.
	 */
	public void update(UserProfile profile) 
			throws JsonException
	{

		// Perform the update
	}

	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username)
		throws JsonException {

		// Perform select
		return null;
	}

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username)
		throws JsonException {

		// TODO
		return false;
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List findRecords(String informationType, String queryString)
		throws JsonException {

		// Todo
		return null;
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date)
		throws JsonException {

		return null;
	}

	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd)
		throws JsonException {
	
		// Perform query
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType)
		throws JsonException {

		// TODO
		return -1;
	}

	/**
	 * Returns a list of all raw data.
	 */
	public List getRawData()
		throws JsonException {

		// TODO
		return null;
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id)
		throws JsonException {
		
		// Todo
		return null;
	}

	public void addRawData(RegularData rd)
		throws JsonException {
		// Todo
		
	}

	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd)
		throws JsonException {

		// TODO 
	}

	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd)
		throws JsonException {

		// TODO 
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords()
		throws JsonException {

		return -1;
	}
}
