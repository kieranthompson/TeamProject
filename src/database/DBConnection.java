package database;

import java.sql.*;
import javax.swing.*;

import static java.lang.Class.forName;


public class DBConnection{



    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    static String public_DNS = "ec2-35-165-180-241.us-west-2.compute.amazonaws.com";
    static String port = ":3306/TeamProject";


    public DBConnection() {

        }

    public static Connection getConnection() {
        try{
            forName("com.mysql.jdbc.Driver");

        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + public_DNS + port, "remoteuser", "imawesome");

        } catch (SQLException sqle){
            sqle.printStackTrace();

        }

        return connection;
    }



    public static void theQuery(String query){


        Statement statement;

        try {

            forName("com.mysql.jdbc.Driver");


        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MySQL JDBC Driver?");

            e.printStackTrace();

            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        Connection connection = null;



        try {

            connection = DriverManager.getConnection("jdbc:mysql://" + public_DNS + port, "remoteuser", "imawesome");

            statement = connection.createStatement();

            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Query Executed");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Connection Failed");

            System.out.println("Connection Failed!:\n" + e.getMessage());

        }

        if (connection != null) {
            System.out.println();

        } else {

            System.out.println("FAILURE! Failed to make connection!");

        }
    }
}											
