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
	
	private boolean insert(String table, JSONObject jsonObject)
	{
		try
		{
			Object obj = parser.parse(new FileReader(this.dbPath));
			JSONArray jsonTables = (JSONArray) obj;
			JSONArray jsonTable = jsonTables.getJSONObject();
			jsonTable.add(jsonObject);
			
			// Save jsonTable to file
			PrintWriter writer = new PrintWriter(this.dbPath, "UTF-8");
			writer.print(jsonTable.toJSONString());
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean insertUserProfile(UserProfile up)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username",  up.getUsername());
		
		return insert("userprofile", jsonObject);
	}
	
	
	
}
