package softarch.portal.db.test;

import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;

import softarch.portal.data.CheapSubscription;
import softarch.portal.data.RawData;
import softarch.portal.data.SoftwareRepository;
import softarch.portal.db.ReadConfigFile;
import softarch.portal.db.json.JsonDB;

public class TestReadConfig {
	public static void main(String[] args) {
		try {
			Map<String,String> properties = new ReadConfigFile().getPropValues();
			System.out.println(properties.toString());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
