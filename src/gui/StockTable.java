package gui;

import java.awt.EventQueue;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class StockTable {

	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockTable window = new StockTable();
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
	public StockTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		table = new JTable();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField Name = new JTextField();
		JTextField Price = new JTextField();
		JTextField StockId = new JTextField();
		
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");
		
		Name.setBounds(20,220,100,25);
		Price.setBounds(20,250,100,25);
		StockId.setBounds(20,280,100,25);
		
		btnAdd.setBounds(150,220,100,25);
		btnDelete.setBounds(150,265,100,25);
		btnUpdate.setBounds(150,310,100,25);
		
		JScrollPane pane = new JScrollPane(table);
		
		
		
	}

}
