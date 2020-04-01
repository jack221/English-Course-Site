/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLConnection {

    private Connection connection;
    
    
    public SQLConnection() {
        
    }

    private Connection connect(String databasename, String hostname, String username, String password) {

        String url = "jdbc:postgresql://" + databasename + "/" + hostname;

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("SQL connect failed");
        }

        return conn;
    }

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = connect("localhost", "postgres", "postgres", "admin");
            }
            if (connection.isClosed()) {
                connection = connect("localhost", "postgres", "postgres", "admin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeServer(Connection c) {
        try {
            c.close();
            System.out.println("server is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
