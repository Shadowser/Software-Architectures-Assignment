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
	
	private void insert(String table, String primarykey, JSONObject jsonObject)
	{
		try
		{
			// Get our database
			Object obj = parser.parse(new FileReader(this.dbPath));
			JSONObject jsonTables = (JSONObject) obj;

			// Get the table 
			JSONObject jsonTable = (JSONObject) jsonTables.get(table);
			
			// Add the record
			jsonTable.put(primarykey, jsonObject);
			
			// Save jsonTable to file
			PrintWriter writer = new PrintWriter(this.dbPath, "UTF-8");
			writer.print(jsonTables.toJSONString());
			writer.close();
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
}
