package softarch.portal.db.json;

import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import softarch.portal.data.UserProfile;

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
			
			// Save jsonTable to file
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
		jsonObject.put("Password", up.getPassword());
		jsonObject.put("FirstName", up.getFirstName());
		jsonObject.put("LastName", up.getLastName());
		jsonObject.put("EmailAddress", up.getEmail());
		jsonObject.put("LastLogin", up.getLastLogin().toString());
		
		String type = up.getClass().getName();
		jsonObject.put("type", type);
			
		insert("users", up.getUsername(), jsonObject);
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
			JSONObject jsonTable = (JSONObject) jsonTables.get("users");
			
			// Get the user
			JSONObject user = (JSONObject) jsonTable.get(username);
			
			// Get the right type to return
			String className = (String)user.get("type");
			up = (UserProfile) Class.forName(className).newInstance();
			
			up.setUsername(username);
			up.setFirstName((String)user.get("FirstName"));
			up.setLastName((String)user.get("LastName"));
			up.setPassword((String)user.get("Password"));
			up.setEmailAddress((String)user.get("EmailAddress"));

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s", Locale.ENGLISH);
			Date date = format.parse((String)user.get("LastLogin"));
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
			JSONObject jsonTable = (JSONObject) jsonTables.get("users");

			// Get the user
			JSONObject user = (JSONObject) jsonTable.get(username);
			
			if(user.get("Password").equals("") || user.get("Password") == null)
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
			
			// Save jsonTable to file
			this.saveJSON(jsonTables.toJSONString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
