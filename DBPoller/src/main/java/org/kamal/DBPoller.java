package org.kamal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by kmuralidharan on 9/21/13.
 */
public class DBPoller {

    public Map<String, String> loadDBProperties() throws IOException {

        Map<String, String> dbproperties = new HashMap<String, String>();
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File(this.getClass().getClassLoader().getResource("db.properties").getFile())));

        for(Map.Entry entry : prop.entrySet()) {
            dbproperties.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return dbproperties;
    }

    public Map<String, Map<String,String>> loadQueryProperties() throws IOException {

        Map<String, Map<String, String>> queryProperties = new HashMap<String, Map<String, String>>();
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File(this.getClass().getClassLoader().getResource("query.properties").getFile())));

        for(Map.Entry entry : prop.entrySet()) {
            String[] queries = entry.getKey().toString().split("#");
            if (queryProperties.containsKey(queries[0])) {
                queryProperties.get(queries[0]).put(queries[1], entry.getValue().toString());
            } else {
                Map<String, String> qmap = new HashMap<String, String>();
                qmap.put(queries[1], entry.getValue().toString());
                queryProperties.put(queries[0], qmap);
            }
        }

        System.out.println(queryProperties.toString());

        return queryProperties;
    }

    public void pollDB() throws IOException {
        Map<String, String> props = loadDBProperties();
        Map<String, Map<String, String>> queryProps
                = loadQueryProperties();

        DBClient client = new DBClient();

        for(Map.Entry<String, String> entry : props.entrySet()) {
            Connection connection = null;
            try {
                connection = client.getDBConnection(entry.getValue());
                Map<String, String> queries = queryProps.get(entry.getKey());
                System.out.println("Today is " + getCurrentDay());
                if (queries.containsKey(getCurrentDay())) {
                    executeQuery(connection, entry.getKey(), queries.get(getCurrentDay()));
                } else {
                    executeQuery(connection, entry.getKey(), queries.get("ALL"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                client.closeDBConnection(connection);
            }
        }
    }

    private String getCurrentDay() {
        String dayNames[] = new DateFormatSymbols().getWeekdays();
        Calendar date1 = Calendar.getInstance();
        return dayNames[date1.get(Calendar.DAY_OF_WEEK)];
    }

    public void executeQuery(Connection con, String key, String query){
        try {
            PreparedStatement ps = con.prepareStatement(query);
            List<JobResult> lst = new ArrayList<JobResult>();
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                JobResult j = new JobResult();
                j.setStatus(resultSet.getString(1));
                lst.add(j);
            }

            DataStore.getMapInstance().put(key, lst);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
