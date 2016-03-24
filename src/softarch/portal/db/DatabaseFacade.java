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


public class DatabaseFacade {
	static Map<String, String> properties = null;
	static IDatabaseFacade dbFacade = null;
	
	/**
	 * Creates a new database facade.
	 * @return 
	 */
	public static IDatabaseFacade getDatabaseFacade(){
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
		return dbFacade;
	}

}
