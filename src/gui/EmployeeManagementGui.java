package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kieran on 17/02/2017.
 */
@SuppressWarnings("all")
public class EmployeeManagementGui extends JFrame {

    public JPanel panel;
    public JLabel empIDLabel, fnameLabel, lnameLabel, passwordLabel;
    public JTextField empIDTextField, fnameTextField, lnameTextField, passwordTextField;
    public JButton createButton, updateButton, removeButton;
    public JComboBox<String> option;

    public String[] empType = {"general", "administrator"};

    public EmployeeManagementGui(){


        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.add(option = new JComboBox<String>(empType));
        panel.add(empIDLabel = new JLabel("Employee ID"));
        panel.add(empIDTextField = new JTextField("", 20));
        panel.add(fnameLabel = new JLabel("First Name: "));
        panel.add(fnameTextField = new JTextField("", 20));
        panel.add(lnameLabel = new JLabel("Last Name: "));
        panel.add(lnameTextField = new JTextField("", 20));
        panel.add(passwordLabel = new JLabel("Password: "));
        panel.add(passwordTextField = new JTextField("", 20));
        panel.add(createButton = new JButton("Create"));
        panel.add(updateButton = new JButton("Update"));
        panel.add(removeButton = new JButton("Remove"));

        add(panel);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBConnection.theQuery("insert into employees(fname, sname, empPassword) values('" + fnameTextField.getText() + "', '" + lnameTextField.getText() + "', '" + passwordTextField.getText() + "');");
                } catch(Exception exc) {
                  exc.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection.theQuery("update employees set fname = '" + fnameTextField.getText() + "', sname = '" + lnameTextField.getText() + "', empPassword = '" + passwordTextField.getText() + "' where empID = " + empIDTextField.getText());
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection.theQuery("delete from employees where empID = " + empIDTextField.getText());
            }
        });
    }


    public static void main(String [] args){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        EmployeeManagementGui frame = new EmployeeManagementGui();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
