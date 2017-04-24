package gui;

import core.*;
import database.DBConnection;

import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class staff extends JFrame{

	private JFrame frame;
	private JTextField fnametf;
	private JTextField lnametf;
	private JTextField idtf;
	private JPasswordField passtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staff window = new staff();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public staff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.setBounds(100, 100, 850, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		fnametf = new JTextField();
		fnametf.setBounds(270, 113, 215, 37);
		this.getContentPane().add(fnametf);
		fnametf.setColumns(10);
		
		lnametf = new JTextField();
		lnametf.setBounds(270, 189, 215, 34);
		this.getContentPane().add(lnametf);
		lnametf.setColumns(10);
		
		idtf = new JTextField();
		idtf.setBounds(270, 335, 215, 34);
		this.getContentPane().add(idtf);
		idtf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(84, 114, 128, 34);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(84, 189, 128, 29);
		this.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Staff ID: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(84, 335, 128, 34);
		this.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Create Employee");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(528, 114, 175, 34);
		this.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Staff Manager: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(273, 24, 212, 52);
		this.getContentPane().add(lblNewLabel_3);
		
		JLabel passwordlbl = new JLabel("Password:");
		passwordlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordlbl.setBounds(84, 273, 128, 24);
		this.getContentPane().add(passwordlbl);
		
		passtf = new JPasswordField();
		passtf.setBounds(270, 268, 215, 34);
		this.getContentPane().add(passtf);
		passtf.setColumns(10);
		
		JButton btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteEmployee.setBounds(528, 225, 175, 34);
		this.getContentPane().add(btnDeleteEmployee);
		
		JButton btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateEmployee.setBounds(528, 335, 175, 34);
		this.getContentPane().add(btnUpdateEmployee);

		JButton exitButton = new javax.swing.JButton("Exit");
		exitButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		exitButton.setBackground(Color.GRAY);
		exitButton.setBounds(473, 466, 121, 34);
		this.getContentPane().add(exitButton);

		JButton listButton = new javax.swing.JButton("List Staff");
		listButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		listButton.setBackground(Color.GRAY);
		listButton.setBounds(614, 466, 121, 34);
		this.getContentPane().add(listButton);

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("insert into employees(fname, sname, empPassword) values('" + fnametf.getText() + "', '" + lnametf.getText() + "', '" + String.valueOf(passtf.getPassword()) + "');");
			}
		});

		btnUpdateEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("update employees set fname = '" + fnametf.getText() + "', sname = '" + lnametf.getText() + "', empPassword = '" + String.valueOf(passtf.getPassword()) + "' where empID = " + Integer.valueOf(idtf.getText()) + ";");
			}
		});

		btnDeleteEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("delete from employees where empID = " + Integer.valueOf(idtf.getText()) + ";");
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ListStaff window = new ListStaff();
					window.initialize();
//					String query = "select * from employees";
//					ArrayList<Employee> employees = new ArrayList<Employee>();
//					Connection connection = DBConnection.getConnection();
//					Statement statement = connection.createStatement();
//					ResultSet rs = statement.executeQuery(query);
//					while(rs.next()) {
//						Employee employee = new Employee();
//						employee.setEmpID(rs.getInt("empID"));
//						employee.setFirstName(rs.getString("fname"));
//						employee.setLastName(rs.getString("sname"));
//						employee.setPassword(rs.getString("empPassword"));
//
//						employees.add(employee);
//
//						System.out.println(employee.getEmpID() + ", " + employee.getFirstName() + ", " + employee.getLastName());
//						System.out.println();

			}
		});


	}
}
