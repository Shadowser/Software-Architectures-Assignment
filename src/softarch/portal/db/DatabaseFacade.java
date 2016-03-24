package softarch.portal.db;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;
import softarch.portal.db.IDatabaseFacade;
import softarch.portal.db.json.DatabaseFacadeJson;
import softarch.portal.db.sql.DatabaseFacadeSQL;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Date;


public class DatabaseFacade implements IDatabaseFacade {
	Map<String, String> properties = null;
	IDatabaseFacade dbFacade = null;
	
	/**
	 * Creates a new database facade.
	 */
	public DatabaseFacade(){
		try {
			properties = new ReadConfigFile().getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(properties.get("database")){
		case "SQL":{
			dbFacade = new DatabaseFacadeSQL(properties.get("username"), properties.get("password"), properties.get("url"));
		}
		break;
		case "JSON":{
			dbFacade = new DatabaseFacadeJson(properties.get("url"));
		}
		break;
		default:{
			//TODO exception 'invalid properties'
		}
		}
	}

	/**
	 * Inserts a new user profile into the user database.
	 */
	public void insert(UserProfile profile) throws DatabaseException
	 {
		// Perform the insert.
		dbFacade.insert(profile);	
	}

	/**
	 * Updates an existing user profile in the user database.
	 */
	public void update(UserProfile profile)  throws DatabaseException
	{
		// Perform the update
		dbFacade.update(profile);
	}

	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username) throws DatabaseException
	{
		// Perform select
		return dbFacade.findUser(username);	
	}

	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username) throws DatabaseException
	{
		return dbFacade.userExists(username);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that match the given query string.
	 */
	public List findRecords(String informationType, String queryString) throws DatabaseException
	{
		return dbFacade.findRecords(informationType, queryString);
	}

	/**
	 * Returns a list containing all records of the given information type
	 * that were added after the given date.
	 */
	public List findRecordsFrom(String informationType, Date date) throws DatabaseException
	{
		return dbFacade.findRecordsFrom(informationType, date);
	}

	/**
	 * Adds a new regular data object to the regular database.
	 */
	public void add(RegularData rd) throws DatabaseException
	{
		// Perform query
		dbFacade.add(rd);
	}

	/**
	 * Returns the number of records of the given information type in the
	 * regular database.
	 */
	public int getNumberOfRegularRecords(String informationType) throws DatabaseException
	{
		return dbFacade.getNumberOfRegularRecords(informationType);
	}

	/**
	 * Returns a list of all raw data.
	 */
	public List getRawData() throws DatabaseException
	{
		return dbFacade.getRawData();
	}

	/**
	 * Returns a specific raw data object.
	 */
	public RawData getRawData(int id) throws DatabaseException
	{
		return dbFacade.getRawData(id);
	}

	public void addRawData(RegularData rd) throws DatabaseException
	{
		dbFacade.addRawData(rd);
	}

	/**
	 * Deletes a raw data object.
	 */
	public void deleteRawData(RawData rd) throws DatabaseException
	{
		dbFacade.deleteRawData(rd);
	}

	/**
	 * Updates a raw data object.
	 */
	public void updateRawData(RawData rd) throws DatabaseException
	{
		dbFacade.updateRawData(rd);
	}

	/**
	 * Returns the number of records in the raw database.
	 */
	public int getNumberOfRawRecords() throws DatabaseException
	{
		return dbFacade.getNumberOfRawRecords();
	}
}
