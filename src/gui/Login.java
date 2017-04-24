package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.event.*;
 

// Practical1A_B IS-A JFrame ==> Inheritance
public class Login extends JFrame{
	// Instance Variables go here (properties of our JFrame)...
   private JPanel imagePanel;
   private ImageIcon login;
   
   private JPanel mainPanel;
   private JPanel admin2;
   private static String currentEmp;
   

   private JPanel staff;
   private JLabel usernameStaff;
   private JTextField usernameS;
   private JLabel passwordStaff;
   private JPasswordField passwordS;
   

 
   
   private static JButton loginStaff;
   private JButton Exit;
  

   
   private String userNameEntered, passwordEntered;
   private final String requiredPassword = "password";

   
   //test commit comment for shaun
   public Login(){
	   
		initialize();
	}
   
   private void initialize(){
      // SET LAYOUT MANAGER to FlowLayout, LEFT justifying
      getContentPane().setLayout(new FlowLayout());
      
     
      staff = new JPanel(new GridLayout(4,1));
      staff.setSize(900, 900);
      staff.add(usernameStaff = new JLabel("Username"));
      staff.add(usernameS = new JTextField(20));
      staff.add(passwordStaff = new JLabel("Password"));
      staff.add(passwordS = new JPasswordField(20));
      passwordS.setEchoChar('*');
      
      staff.add(Exit = new JButton("Exit"));
      staff.add(loginStaff = new JButton("Login"));

    
      
     staff.setBorder(new TitledBorder("Staff Login"));
      getContentPane().add(staff, BorderLayout.CENTER);
     // add(button, BorderLayout.SOUTH);
      
      loginStaff.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e) {
                try{
                   Connection connection = DBConnection.getConnection();

                   PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees where empID = ? and empPassword = ?");
                   preparedStatement.setInt(1, Integer.valueOf(usernameS.getText()));
                   preparedStatement.setString(2, String.valueOf(passwordS.getPassword()));
                   ResultSet rs = preparedStatement.executeQuery();

                   if(rs.next()){
                      GeneralWindow window = new GeneralWindow();
                      window.setVisible(true);
                      dispose();
                      GeneralWindow.currentEmployee.setText(String.valueOf(rs.getInt("empID")) + ": " + rs.getString("fname") + " " + rs.getString("sname"));



                   } else {
                      PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM admin where adminID = ? and adminPassword = ?");
                      preparedStatement2.setInt(1, Integer.valueOf(usernameS.getText()));
                      preparedStatement2.setString(2, String.valueOf(passwordS.getPassword()));
                      ResultSet rs2 = preparedStatement2.executeQuery();

                      if(rs2.next()) {
                         GeneralWindow window = new GeneralWindow();
                         window.setVisible(true);
                         dispose();
                         GeneralWindow.currentEmployee.setText(String.valueOf(rs.getInt("adminID")) + ": " + rs.getString("fname") + " " + rs.getString("sname"));
                      }
                      else{
                         System.out.println("NOPE");
                      }
                   }
                }catch(SQLException sqle) {
                   sqle.printStackTrace();
                }

               }
            });
   }

	
   public static void main(String[] args){
   
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
         		.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
         run();
      } 
      catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(Login.class.getName()).log(
            	java.util.logging.Level.SEVERE, null, ex);
      } 
      catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Login.class.getName()).log(
            	java.util.logging.Level.SEVERE, null, ex);
      } 
      catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Login.class.getName()).log(
            	java.util.logging.Level.SEVERE, null, ex);
      } 
      catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Login.class.getName()).log(
            	java.util.logging.Level.SEVERE, null, ex);
      }



   }

   public static void run(){
      Login frame = new Login();
      frame.pack();
      frame.setTitle("Login");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      frame.getRootPane().setDefaultButton(loginStaff);
   }
}