package gui;

import java.sql.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import database.DBConnection;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTable;


public class ListStaff {
	private JFrame frame;
	private JTextField fName;
	private JTextField sName;
	private JTextField empId;
	private static JPasswordField password;
	private static JTextField textField;
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
		initialize();

	}
	public static void initialize() {
		JFrame frame = new JFrame();
		JTable table= new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setBounds(428, 445, 396, -328);
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		Object[] columns = {"Employee Id", "First Name", "Last Name"};
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);


		table.setBackground(Color.white);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table.setRowHeight(30);

		JTextField empId = new JTextField();
		JTextField fName = new JTextField();
		JTextField sName = new JTextField();

		empId.setBounds(41, 413, 174, 38);
		fName.setBounds(225, 413, 229, 38);
		sName.setBounds(41, 513, 174, 39);

		JScrollPane pane = new JScrollPane(table);

		pane.setBounds(41, 32, 680, 302);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);

		frame.getContentPane().add(empId);
		frame.getContentPane().add(fName);
		frame.getContentPane().add(sName);

		JLabel lblAddId = new JLabel("Employee ID");
		lblAddId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddId.setBounds(41, 369, 109, 33);
		frame.getContentPane().add(lblAddId);

		JLabel lblFName = new JLabel("First Name");
		lblFName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFName.setBounds(225, 369, 100, 33);
		frame.getContentPane().add(lblFName);

		JLabel lblLName = new JLabel("Last Name");
		lblLName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLName.setBounds(41, 475, 100, 33);
		frame.getContentPane().add(lblLName);

		JButton btnListStaff = new JButton("List Staff");
		btnListStaff.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListStaff.setBounds(529, 363, 120, 39);
		frame.getContentPane().add(btnListStaff);

		JButton btnCreateEmployee = new JButton("Create Employee");
		btnCreateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateEmployee.setBounds(479, 413, 230, 38);
		frame.getContentPane().add(btnCreateEmployee);

		JButton btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateEmployee.setBounds(479, 462, 230, 38);
		frame.getContentPane().add(btnUpdateEmployee);

		JButton btnDeleteEmplyoee = new JButton("Delete Emplyoee");
		btnDeleteEmplyoee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteEmplyoee.setBounds(480, 511, 229, 38);
		frame.getContentPane().add(btnDeleteEmplyoee);

		password = new JPasswordField();
		password.setBounds(225, 512, 229, 38);
		frame.getContentPane().add(password);
		password.setColumns(10);

		JLabel lblPasssword = new JLabel("Password");
		lblPasssword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPasssword.setBounds(225, 480, 100, 22);
		frame.getContentPane().add(lblPasssword);

		Object[] row = new Object[3];

		table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();
				empId.setText(model.getValueAt(i, 0). toString());
				fName.setText(model.getValueAt(i, 1).toString());
				sName.setText(model.getValueAt(i, 2).toString());
				//description.setText(model.getValueAt(i,  2).toString());
			}
		});

		btnListStaff.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				int i = table.getSelectedRow();
				try{
					Connection connection = DBConnection.getConnection();
					Statement statement = connection.createStatement();
					ResultSet rs;
					model.setRowCount(0);
					if(empId.getText().equalsIgnoreCase(""))

						rs = statement.executeQuery("select * from employees;");

					else
						rs = statement.executeQuery("select * from employees where empID = " + empId.getText() + ";");

					while(rs.next()){
						row[0] = rs.getInt("empID");
						row[1] = rs.getString("fName");
						row[2] = rs.getString("sName");

						model.addRow(row);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();
				empId.setText(model.getValueAt(i, 0). toString());
				fName.setText(model.getValueAt(i, 1).toString());
				sName.setText(model.getValueAt(i, 2).toString());

			}
		});

		btnCreateEmployee.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("insert into employees(fname, sname, empPassword) values('" + fName.getText() + "', '" + sName.getText() + "', '" + String.valueOf(password.getPassword()) + "');");
			}
		});

		btnUpdateEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("update employees set fname = '" + fName.getText() + "', sname = '" + sName.getText() + "', empPassword = '" + String.valueOf(((JPasswordField) password).getPassword()) + "' where empID = " + Integer.valueOf(empId.getText()) + ";");
			}
		});

		btnDeleteEmplyoee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection.theQuery("delete from employees where empID = " + Integer.valueOf(empId.getText()) + ";");
			}
		});

		frame.setSize(771, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}