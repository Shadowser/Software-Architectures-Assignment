package softarch.portal.db.json;

import java.io.FileReader;
import java.io.PrintWriter;

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
	
	private void insert(String table, JSONObject jsonObject)
	{
		try
		{
			// Get our database
			Object obj = parser.parse(new FileReader(this.dbPath));
			JSONObject jsonTables = (JSONObject) obj;

			// Get the table 
			JSONArray jsonTable = (JSONArray) jsonTables.get(table);
			
			// Add the record
			jsonTable.add(jsonObject);
			
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
		jsonObject.put("Username",  up.getUsername());
		jsonObject.put("Password", up.getPassword());
		jsonObject.put("FirstName", up.getFirstName());
		jsonObject.put("LastName", up.getLastName());
		jsonObject.put("EmailAddress", up.getEmail());
		jsonObject.put("LastLogin", up.getLastLogin().toString());
		
		String type = up.getClass().getName();
		jsonObject.put("type", type);
			
		insert("users", jsonObject);
	}
}
