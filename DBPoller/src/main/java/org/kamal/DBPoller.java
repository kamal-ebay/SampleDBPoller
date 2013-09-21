package org.kamal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by kmuralidharan on 9/21/13.
 */
public class DBPoller {

    public Map<String, String> loadProperties() throws IOException {

        Map<String, String> dbconn = new HashMap<String, String>();
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("db.properties")));

        for(Map.Entry entry : prop.entrySet()) {
            dbconn.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return dbconn;
    }

}
