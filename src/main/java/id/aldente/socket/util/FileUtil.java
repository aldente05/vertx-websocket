package id.aldente.socket.util;

import com.prudential.platform.stream.compute.NonBlocking;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileUtil {

	public static Map<String, String> readProperties(String filePath) {
		Map<String, String> mapProperties = null;

        Properties props = new Properties();
        InputStream fis = null;

        try {
            fis = new FileInputStream(filePath);

            props.load(fis);

            @SuppressWarnings("unchecked")
            Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
            mapProperties = new HashMap<>();
            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                String value = props.getProperty(key);
                mapProperties.put(key, value);            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return mapProperties;
	}

	@NonBlocking
	public static AppConfig getConfig(Map<String, String> mapProperties,String prefix) {
		AppConfig config = new AppConfig();

		 try {

	         	if (mapProperties.get(prefix+"dataSourceName") != null) {
	         		config.setDataSourceName(mapProperties.get(prefix+"dataSourceName"));
	         	}

	         	if (mapProperties.get(prefix+"datasourceUsername") != null) {
	         		config.setDatasourceUsername(mapProperties.get(prefix+"datasourceUsername"));
	         	}

	         	if (mapProperties.get(prefix+"datasourcePassword") != null) {
	         		config.setDatasourcePassword(mapProperties.get(prefix+"datasourcePassword"));
	         	}

	         	if (mapProperties.get(prefix+"databaseName") != null) {
	         		config.setDatabaseName(mapProperties.get(prefix+"databaseName"));
	         	}

	         	if (mapProperties.get(prefix+"databasePort") != null) {
	         		config.setDatabasePort(mapProperties.get(prefix+"databasePort"));
	         	}

	         	if (mapProperties.get(prefix+"databaseHost") != null) {
	         		config.setDatabaseHost(mapProperties.get(prefix+"databaseHost"));
	         	}

	         	if (mapProperties.get(prefix+"dataSourceClassName") != null) {
	         		config.setDataSourceClassName(mapProperties.get(prefix+"dataSourceClassName"));
	         	}

	         	if (mapProperties.get(prefix+"maxActive") != null) {
	         		config.setMaxActive(Integer.valueOf(mapProperties.get(prefix+"maxActive")));
	         	}

	         	if (mapProperties.get(prefix+"initialSize") != null) {
	         		config.setInitialSize(Integer.valueOf(mapProperties.get(prefix+"initialSize")));
	         	}

	         	//tokenValidateURL
	         	if (mapProperties.get(prefix+"pruhubBaseValidateToken") != null) {
	         		config.setTokenValidateURL(mapProperties.get(prefix+"pruhubBaseValidateToken"));
	         	}

	         	//encryptionUrl
	         	if (mapProperties.get(prefix+"encryptionUrl") != null) {
	         		config.setEncryptionUrl(mapProperties.get(prefix+"encryptionUrl"));
	         	}

	         	//operationName
	         	if(mapProperties.get(prefix+"operationName") != null) {
	         		config.setEncryptionUrl(mapProperties.get(prefix+"operationName"));
	         	}


		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return config;
	}
}
