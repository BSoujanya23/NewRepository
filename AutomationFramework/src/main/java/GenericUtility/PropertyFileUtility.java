package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of methods related to propertyFile
 */
public class PropertyFileUtility {
/**
 * This method is used to read the data from property file provided key
 * @param key
 * @return
 * @throws IOException
 */
	public String toReadDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	
	}

}
