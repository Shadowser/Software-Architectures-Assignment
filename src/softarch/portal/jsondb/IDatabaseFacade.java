package softarch.portal.jsondb;

import java.util.Date;
import java.util.List;

import softarch.portal.data.RawData;
import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;

public interface IDatabaseFacade {
	
	public void insert(UserProfile profile) throws JsonException;

	public void update(UserProfile profile);

	public UserProfile findUser(String username);

	public boolean userExists(String username);

	public List findRecords(String informationType, String queryString);

	public List findRecordsFrom(String informationType, Date date);

	public void add(RegularData rd);
	
	public int getNumberOfRegularRecords(String informationType);
	
	public List getRawData();
		
	public RawData getRawData(int id);
	
	public void addRawData(RegularData rd);
		
	public void deleteRawData(RawData rd);
		
	public void updateRawData(RawData rd);
		
	public int getNumberOfRawRecords();
}
