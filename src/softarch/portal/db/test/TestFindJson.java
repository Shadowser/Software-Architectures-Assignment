package softarch.portal.db.test;

import java.net.URL;
import java.util.Date;

import org.json.simple.JSONObject;

import softarch.portal.data.CheapSubscription;
import softarch.portal.data.RawData;
import softarch.portal.data.SoftwareRepository;
import softarch.portal.data.UserProfile;
import softarch.portal.db.json.JsonDB;

public class TestFindJson {
	public static void main(String[] args) {
		try {
			JsonDB jsonDB = new JsonDB("jsondatabase.json");
			//CheapSubscription test = new CheapSubscription("UsernameTest", "PasswordTest", "FirstNameTest", "LastNameTest", "EmailTest", new Date());
			UserProfile user = jsonDB.findUser("test");
			System.out.println(user.toString());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
