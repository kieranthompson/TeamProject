package gui;

import core.Employee;

import database.DBConnection;
import gui.GeneralWindow;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AdminLogin {

	public static JFrame frame;
	private static JTextField txtUsername;
	private static JPasswordField txtPassword;
	public static boolean isLoggedIn;
	static Connection connection;

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
					AdminLogin window = new AdminLogin();
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
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		txtUsername = new JTextField();
		txtUsername.setBounds(370, 165, 174, 41);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(370, 254, 174, 41);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setBounds(370, 350, 174, 31);
		frame.getContentPane().add(btnNewButton);

		JLabel lblLoginAsAdministrator = new JLabel("Login as Administrator");
		lblLoginAsAdministrator.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginAsAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginAsAdministrator.setBounds(343, 51, 227, 79);
		frame.getContentPane().add(lblLoginAsAdministrator);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(251, 165, 59, 41);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(251, 254, 59, 41);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getRootPane().setDefaultButton(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DBConnection.getConnection();

					PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees where empID = ? and empPassword = ?");
					preparedStatement.setInt(1, Integer.valueOf(txtUsername.getText()));
					preparedStatement.setString(2, String.valueOf(txtPassword.getPassword()));
					ResultSet rs = preparedStatement.executeQuery();

					if(rs.next()){
						isLoggedIn = true;
						frame.dispose();


					} else {
						isLoggedIn = false;
						PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM admin where adminID = ? and adminPassword = ?");
						preparedStatement2.setInt(1, Integer.valueOf(txtUsername.getText()));
						preparedStatement2.setString(2, String.valueOf(txtPassword.getPassword()));
						ResultSet rs2 = preparedStatement2.executeQuery();

						if(rs2.next()) {
							GeneralWindow window = new GeneralWindow();
							window.setVisible(true);
							frame.dispose();
							JOptionPane.showMessageDialog(null, "Welcome \nAdministrator: " + rs2.getString("fname"));
						}
						else{
							System.out.println("NOPE");
						}
					}

				} catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}
		});

	}

}