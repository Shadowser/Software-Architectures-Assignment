package softarch.portal.db.json;

import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import softarch.portal.data.RegularData;
import softarch.portal.data.UserProfile;
import softarch.portal.db.DatabaseContract;

public class JsonDB {

	private String dbPath = "";
	private JSONParser parser = new JSONParser();
	
	public JsonDB(String path)
	{
		this.dbPath = path;
	}
	
	private JSONObject getJsonTables()
	{
		JSONObject obj = null;
		try
		{
			obj = (JSONObject) parser.parse(new FileReader(this.dbPath));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
	
	private void saveJSON(String json)
	{
		try
		{
			PrintWriter writer = new PrintWriter(this.dbPath, "UTF-8");
			writer.print(json);
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void insert(String table, String primarykey, JSONObject jsonObject)
	{
		try
		{
			// Get our database
			JSONObject jsonTables = this.getJsonTables();

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(table);
			
			// Add the record
			jsonTable.put(primarykey, jsonObject);
			
			// Save jsonTable to file thanks to pointers
			this.saveJSON(jsonTables.toJSONString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void insertUserProfile(UserProfile up)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(DatabaseContract.Table_Users.COL_PASSWORD, up.getPassword());
		jsonObject.put(DatabaseContract.Table_Users.COL_FIRST_NAME, up.getFirstName());
		jsonObject.put(DatabaseContract.Table_Users.COL_LAST_NAME, up.getLastName());
		jsonObject.put(DatabaseContract.Table_Users.COL_EMAIL, up.getEmail());
		jsonObject.put(DatabaseContract.Table_Users.COL_LAST_LOGIN, up.getLastLogin().toString());
		
		String type = up.getClass().getName();
		jsonObject.put(DatabaseContract.Table_Users.COL_TYPE, type);
			
		insert(DatabaseContract.Table_Users.TABLE_NAME, up.getUsername(), jsonObject);
	}
	
	public List<RegularData> findRecords(String informationType, String queryString){
		List<RegularData> regularDatas = new ArrayList<RegularData>();
		try
		{
			
			// Get our database
			JSONObject jsonTables = this.getJsonTables();

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(DatabaseContract);
			
			
			// Get the user
			//JSONObject user = (JSONObject) jsonTable.get(username);
			for (JSONObject e : jsonTable) {
				if (e.get().contains(queryString) {
				   //add to my result list
					regularDatas.add(e);
			  }
			}
			
			// Get the right type to return
			String className = (String)user.get(DatabaseContract.Table_Users.COL_TYPE);
			up = (UserProfile) Class.forName(className).newInstance();
			
			up.setUsername(username);
			up.setFirstName((String)user.get(DatabaseContract.Table_Users.COL_FIRST_NAME));
			up.setLastName((String)user.get(DatabaseContract.Table_Users.COL_LAST_NAME));
			up.setPassword((String)user.get(DatabaseContract.Table_Users.COL_PASSWORD));
			up.setEmailAddress((String)user.get(DatabaseContract.Table_Users.COL_EMAIL));

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s", Locale.ENGLISH);
			Date date = format.parse((String)user.get(DatabaseContract.Table_Users.COL_LAST_LOGIN));
			up.setLastLogin(date);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return up;
	}
	
	public UserProfile findUser(String username)
	{
		UserProfile up = null;
		try
		{
			// Get our database
			Object obj = parser.parse(new FileReader(this.dbPath));
			JSONObject jsonTables = (JSONObject) obj;

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(DatabaseContract.Table_Users.TABLE_NAME);
			
			// Get the user
			JSONObject user = (JSONObject) jsonTable.get(username);
			
			// Get the right type to return
			String className = (String)user.get(DatabaseContract.Table_Users.COL_TYPE);
			up = (UserProfile) Class.forName(className).newInstance();
			
			up.setUsername(username);
			up.setFirstName((String)user.get(DatabaseContract.Table_Users.COL_FIRST_NAME));
			up.setLastName((String)user.get(DatabaseContract.Table_Users.COL_LAST_NAME));
			up.setPassword((String)user.get(DatabaseContract.Table_Users.COL_PASSWORD));
			up.setEmailAddress((String)user.get(DatabaseContract.Table_Users.COL_EMAIL));

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s", Locale.ENGLISH);
			Date date = format.parse((String)user.get(DatabaseContract.Table_Users.COL_LAST_LOGIN));
			up.setLastLogin(date);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return up;
	}
	
	
	
	public boolean userExists(String username)
	{
		try
		{
			// Get our database
			Object obj = parser.parse(new FileReader(this.dbPath));
			JSONObject jsonTables = (JSONObject) obj;

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(DatabaseContract.Table_Users.TABLE_NAME);

			// Get the user
			JSONObject user = (JSONObject) jsonTable.get(username);
			
			// We can assume that if the password is empty
			// That the user does not exist.
			if(user.get(DatabaseContract.Table_Users.COL_PASSWORD).equals("") || user.get(DatabaseContract.Table_Users.COL_PASSWORD) == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void updateUserProfile(UserProfile up)
	{
		this.delete(DatabaseContract.Table_Users.TABLE_NAME, up.getUsername());
		this.insertUserProfile(up);
	}
	
	public void delete(String table, String primarykey)
	{
		try
		{
			// Get our database
			JSONObject jsonTables = this.getJsonTables();

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(table);
			
			// Delete the record
			jsonTable.remove(primarykey);
			
			// Save jsonTable to file thanks to pointers
			this.saveJSON(jsonTables.toJSONString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void insertRegular(RegularData rd) {
		// TODO Auto-generated method stub
		
	}
}
