package gui;


	import java.awt.EventQueue;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
	import java.awt.Font;


	public class StaffLogin extends javax.swing.JFrame {
		
		private static final long serialVersionUID = 1L;
		//Declare Variables
		private javax.swing.JFrame frame;
		private javax.swing.JTextField usernameTxtField;
		private javax.swing.JTextField passwordTxtField;
		private javax.swing.JLabel usernameLabel;
		private javax.swing.JLabel passwordLabel;
	    private javax.swing.JButton exitButton;
	    private javax.swing.JButton loginButton;
	    
	    private javax.swing.JLabel loginLabel;

	 
	    	
	    	
	    
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try{
	    		for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
	    			if
	    			("Nimbus".equals(info.getName())){
	    				UIManager.setLookAndFeel(info.getClassName());
	    				break;
	    				
	    			}
	    	}
	    }
		catch(Exception e) {
			//if nimbus is not available, you can set the GUI to another look and feel.
		
		}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StaffLogin window = new StaffLogin();
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
		public StaffLogin() {
			GUI();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void GUI() {
			frame = new JFrame();
			frame.setBounds(100, 100, 672, 331);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			
			usernameTxtField = new javax.swing.JTextField();
			usernameTxtField.setBounds(300, 65, 138, 33);
			frame.getContentPane().add(usernameTxtField);
			usernameTxtField.setColumns(10);
			
			passwordTxtField = new javax.swing.JTextField();
			passwordTxtField.setBounds(300, 112, 138, 33);
			frame.getContentPane().add(passwordTxtField);
			passwordTxtField.setColumns(10);
			
			
			usernameLabel = new javax.swing.JLabel("Username:");
			usernameLabel.setFont(new Font("Kalinga", Font.BOLD, 11));
			usernameLabel.setBounds(200, 68, 123, 14);
			frame.getContentPane().add(usernameLabel);
			
			
			passwordLabel = new javax.swing.JLabel("Password:");
			passwordLabel.setFont(new Font("Kalinga", Font.BOLD, 11));
			passwordLabel.setBounds(200, 115, 128, 14);
			frame.getContentPane().add(passwordLabel);
			
			
			exitButton = new javax.swing.JButton("Exit");
			exitButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			//exitButton.setBackground(Color.GRAY);
			exitButton.setBounds(350, 222, 89, 23);
			frame.getContentPane().add(exitButton);
			
			loginButton = new javax.swing.JButton("Login");
			loginButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			//loginButton.setBackground(Color.GRAY);
			loginButton.setBounds(200, 222, 89, 23);
			frame.getContentPane().add(loginButton);
			
			loginLabel = new javax.swing.JLabel("Staff Login");
			loginLabel.setFont(new Font("Dialog", Font.BOLD, 22));
			//loginLabel.setForeground(Color.DARK_GRAY);
			loginLabel.setBounds(300, 27, 150, 27);
			frame.getContentPane().add(loginLabel);
			
			

		}
		

	}


