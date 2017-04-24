package gui;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.Font;


public class Search extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	//Declare Variables
	private javax.swing.JFrame frame;
	private javax.swing.JTextField idTextField;
	private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel idLabel;
	private javax.swing.JLabel nameLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;


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
					Search window = new Search();
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
	public Search() {
		GUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void GUI() {
		frame = new JFrame();
		//frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		//frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 672, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		idTextField = new javax.swing.JTextField();
		idTextField.setBounds(300, 65, 138, 33);
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		nameTextField = new javax.swing.JTextField();
		nameTextField.setBounds(300, 112, 138, 33);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		idLabel = new javax.swing.JLabel("Product ID:");
		idLabel.setFont(new Font("Kalinga", Font.BOLD, 11));
		idLabel.setBounds(200, 68, 123, 14);
		frame.getContentPane().add(idLabel);
		
		
		nameLabel = new javax.swing.JLabel("Product Name:");
		nameLabel.setFont(new Font("Kalinga", Font.BOLD, 11));
		nameLabel.setBounds(200, 115, 128, 14);
		frame.getContentPane().add(nameLabel);
		
		exitButton = new javax.swing.JButton("Exit");
		exitButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		//exitButton.setBackground(Color.GRAY);
		exitButton.setBounds(350, 222, 89, 23);
		frame.getContentPane().add(exitButton);
		
		searchButton = new javax.swing.JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		//searchButton.setBackground(Color.GRAY);
		searchButton.setBounds(200, 222, 89, 23);
		frame.getContentPane().add(searchButton);
		
		searchLabel = new javax.swing.JLabel("SEARCH");
		searchLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		//searchLabel.setForeground(Color.DARK_GRAY);
		searchLabel.setBounds(320, 27, 104, 27);
		frame.getContentPane().add(searchLabel);
		
	

	}
	

}


