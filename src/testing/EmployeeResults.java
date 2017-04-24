package testing;

import database.DBConnection;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Kieran on 17/02/2017.
 */
public class EmployeeResults extends JFrame {

    JPanel panel;
    JTextArea results;
    DBConnection connection;
    Statement statement;
    ResultSet resultSet;

    public static void main(String [] args) {
       listEmployees();
       insertIntoEmployees("TEST", "TEST");
       listEmployees();

    }

    public static void listEmployees() {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employees;");

            while(resultSet.next()) {
                System.out.println(resultSet.getString("fname") + ", " + resultSet.getString("sname"));

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void insertIntoEmployees(String fname, String lname) {

        try {

            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employees(fname, sname) values(?, ?);");
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

}
