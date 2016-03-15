package softarch.portal.db.json;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;

public interface IDatabaseFacade {
	
	public void insert(UserProfile profile) throws JsonException;

	public void update(UserProfile profile) throws JsonException;

	public UserProfile findUser(String username) throws JsonException;

	public boolean userExists(String username) throws JsonException;

	public List findRecords(String informationType, String queryString) throws JsonException;

	public List findRecordsFrom(String informationType, Date date) throws JsonException;

	public void add(RegularData rd) throws JsonException;
	
	public int getNumberOfRegularRecords(String informationType) throws JsonException;
	
	public List getRawData() throws JsonException;
		
	public RawData getRawData(int id) throws JsonException;
	
	public void addRawData(RegularData rd) throws JsonException;
		
	public void deleteRawData(RawData rd) throws JsonException;
		
	public void updateRawData(RawData rd) throws JsonException;
		
	public int getNumberOfRawRecords() throws JsonException;
}
