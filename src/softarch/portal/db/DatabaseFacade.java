package softarch.portal.db;

import softarch.portal.db.IDatabaseFacade;
import softarch.portal.db.json.DatabaseFacadeJson;
import softarch.portal.db.sql.DatabaseFacadeSQL;

import java.util.Map;
import java.io.IOException;


public class DatabaseFacade {
	
	private static Map<String, String> properties = null;
	private static IDatabaseFacade dbFacade = null;
	
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
			dbFacade = new DatabaseFacadeSQL(properties.get("username"), properties.get("password"), properties.get("dburl"));
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
