package org.kamal;

import java.sql.*;

/**
 * Created by kmuralidharan on 9/19/13.
 */
public class DBClient {
    public Connection getDBConnection(String connectionString) throws Exception {

        Connection connection =  null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("MySQL Driver is not found in the classpath");
        }

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/test","root", "root");

        } catch (SQLException e) {
            e.printStackTrace();;
            throw new Exception("Unable to establish connection with the Database.");
        }

       return connection;
    }

}

