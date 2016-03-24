package softarch.portal.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
//source: http://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
public class ReadConfigFile {
	InputStream inputStream;
 
	public Map<String, String> getPropValues() throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			String propFileName = "resources/config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			result.put("database", prop.getProperty("database"));
			result.put("url", prop.getProperty("url"));
			result.put("username", prop.getProperty("username"));
			result.put("password", prop.getProperty("password"));
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
