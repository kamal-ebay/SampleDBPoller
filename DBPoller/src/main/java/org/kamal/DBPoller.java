package org.kamal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by kmuralidharan on 9/21/13.
 */
public class DBPoller {

    public Map<String, String> loadProperties() throws IOException {

        Map<String, String> dbproperties = new HashMap<String, String>();
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File(this.getClass().getClassLoader().getResource("db.properties").getFile())));

        for(Map.Entry entry : prop.entrySet()) {
            dbproperties.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return dbproperties;
    }

    public void pollDB() throws IOException {
        Map<String, String> props = loadProperties();
        DBClient client = new DBClient();

        for(Map.Entry<String, String> entry : props.entrySet()) {
            Connection connection = null;
            try {
                connection = client.getDBConnection(entry.getValue());
                executeQuery(connection);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                client.closeDBConnection(connection);
            }
        }
    }

    public void executeQuery(Connection con){
        try {
            PreparedStatement ps = con.prepareStatement("select status from jobs");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                System.out.println("Status is " + resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String s[]) throws IOException {
        DBPoller poller = new DBPoller();
        poller.pollDB();
    }
}
