package gui;

import core.Customer;
import core.Email;
import database.DBConnection;
import core.Email;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class CustomersWindow extends JFrame {

	private JFrame frame;
	private JTextField fNameField;
	private JTextField sNameField;
	private JTextField addressField1;
	private JTextField addressField2;
	private JTextField emailField;
	static JButton btnEnter = new JButton("Enter");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomersWindow window = new CustomersWindow();
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
	public CustomersWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.setBounds(100, 100, 397, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(23, 55, 131, 28);
		this.getContentPane().add(lblFirstName);
		
		JLabel lblSecondName = new JLabel("Second Name:");
		lblSecondName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSecondName.setBounds(23, 113, 131, 28);
		this.getContentPane().add(lblSecondName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(23, 175, 131, 28);
		this.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(23, 290, 107, 28);
		this.getContentPane().add(lblEmail);
		
		fNameField = new JTextField();
		fNameField.setBounds(185, 55, 167, 28);
		this.getContentPane().add(fNameField);
		fNameField.setColumns(10);
		
		sNameField = new JTextField();
		sNameField.setBounds(185, 113, 167, 28);
		this.getContentPane().add(sNameField);
		sNameField.setColumns(10);
		
		addressField1 = new JTextField();
		addressField1.setBounds(186, 175, 166, 28);
		this.getContentPane().add(addressField1);
		addressField1.setColumns(10);
		
		addressField2 = new JTextField();
		addressField2.setBounds(185, 227, 167, 28);
		this.getContentPane().add(addressField2);
		addressField2.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(185, 290, 167, 28);
		this.getContentPane().add(emailField);
		emailField.setColumns(10);
		

		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnter.setBounds(158, 390, 99, 38);
		this.getContentPane().add(btnEnter);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(23, 390, 107, 38);
		this.getContentPane().add(btnCancel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(282, 390, 89, 38);
		getContentPane().add(btnExit);

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Customer customer = new Customer();
				customer.setFname(fNameField.getText());
				customer.setSname(sNameField.getText());
				customer.setAddress(addressField1.getText() + " " + addressField2.getText());
				customer.setEmail(emailField.getText());
				String currentCustomerEmail = emailField.getText();
				StringBuilder receiptContent = new StringBuilder();


				try {

					Connection connection = DBConnection.getConnection();
					PreparedStatement ps = connection.prepareStatement("insert into customer(fname, sname, address, email) values(?, ?, ?, ?);");

					ps.setString(1, customer.getFname());
					ps.setString(2, customer.getSname());
					ps.setString(3, customer.getAddress());
					ps.setString(4, customer.getEmail());
					ps.executeUpdate();
					GeneralWindow.totalLabel.setText("Total: ");
					double total = GeneralWindow.totalPrice;
					GeneralWindow.totalPrice = 0;


					Email email = new Email();
					try {
						email.emailReceipt("electroworld.letterkenny@gmail.com", "imawesome123", customer.getEmail(), "ElectroWorld Receipt", customer.getFname() + " " + customer.getSname() + "\nThank you for shopping at Electro-World!" + "\nReceipt No: " + GeneralWindow.transactionID + GeneralWindow.receiptDetails + "\nTotal: €" + total);
						GeneralWindow.reset();
					} catch (MessagingException e1) {
						e1.printStackTrace();
					}


				} catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
