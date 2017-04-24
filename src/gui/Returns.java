package gui;

import java.awt.Color;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import database.DBConnection;
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

public class Returns {

	static String selectedItem = "";
	static String transactionId = "";
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
		table.setBounds(428, 445, 396, -328);

		Object[] columns = {"Product Id", "Product Name", "Price", "Transaction ID"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);

		table.setBackground(Color.white);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table.setRowHeight(30);

		JTextField reciptId = new JTextField();
		JButton btnExit = new JButton("Exit");

		reciptId.setBounds(20, 56, 230, 38);
		btnExit.setBounds(20, 511, 100, 39);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(281, 32, 440, 518);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);

		frame.getContentPane().add(reciptId);
		frame.getContentPane().add(btnExit);

		JLabel lblReceiptId = new JLabel("Receipt ID");
		lblReceiptId.setBounds(20, 32, 158, 14);
		frame.getContentPane().add(lblReceiptId);

		JButton btnFindTransaction = new JButton("Find Transaction");
		btnFindTransaction.setBounds(20, 105, 158, 39);
		frame.getContentPane().add(btnFindTransaction);
		
		JButton btnUnwantedunopened = new JButton("Unwanted/Unopened");
		btnUnwantedunopened.setBounds(20, 211, 158, 38);
		frame.getContentPane().add(btnUnwantedunopened);
		
		JButton btnFaulty = new JButton("Faulty");
		btnFaulty.setBounds(20, 260, 158, 38);
		frame.getContentPane().add(btnFaulty);

		Object[] row = new Object[4];
		table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();
				reciptId.setText(model.getValueAt(i, 0). toString());
			}
		});

		btnUnwantedunopened.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = DBConnection.getConnection();
				try {
					selectedItem = Integer.toString((int) model.getValueAt(table.getSelectedRow(), 0));
					transactionId = Integer.toString((int) model.getValueAt(table.getSelectedRow(), 3));
				
					PreparedStatement ps = connection.prepareStatement("update stock set quantity = quantity + 1 where stockID = ?;");

					PreparedStatement ps2 = connection.prepareStatement("delete from receipt where itemName = ? and transactionID = ?;");
						ps.setInt(1, Integer.valueOf(selectedItem));
						ps2.setString(1, String.valueOf(model.getValueAt(table.getSelectedRow(), 1)));
						ps2.setInt(2, Integer.valueOf(transactionId));
						ps.executeUpdate();
						ps2.executeUpdate();
						model.removeRow(table.getSelectedRow());
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnFaulty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Connection connection = DBConnection.getConnection();
				int confirmFaulty = JOptionPane.showConfirmDialog(null, "Has the product been tested?");
				if (confirmFaulty == JOptionPane.YES_OPTION) {
					try {
						transactionId = Integer.toString((int) model.getValueAt(table.getSelectedRow(), 3));
						PreparedStatement ps = connection.prepareStatement("delete from receipt where itemName = ? and transactionID = ?");
						ps.setString(1, String.valueOf(model.getValueAt(table.getSelectedRow(), 1)));
						ps.setInt(2, Integer.valueOf(transactionId));
						ps.executeUpdate();
						model.removeRow(table.getSelectedRow());
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Check Item First");
				}
			}

		});

		btnExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				frame.dispose();

			}
		});

		btnFindTransaction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				model.setRowCount(0);
				int i = table.getSelectedRow();
				try{
					
					Connection connection = DBConnection.getConnection();
					Statement statement = connection.createStatement();
					Statement statement2 = connection.createStatement();
					ResultSet rs, rs2;
					//model.setRowCount(0);
					if(reciptId.getText().equalsIgnoreCase(""))

						rs = statement.executeQuery("select * from receipt;");

					else
						rs = statement.executeQuery("select * from receipt where transactionID = " + reciptId.getText() + ";");
						
						while(rs.next()){
							rs2 = statement2.executeQuery("select stockID from stock where sname = '" + rs.getString("itemName") + "';");
							
							while(rs2.next()){
						
								row[0] = rs2.getInt("stockID");
								row[1] = rs.getString("itemName");
								row[2] = rs.getString("itemPrice");
								row[3] = rs.getInt("transactionID");
							
						model.addRow(row);
							}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		frame.setSize(771, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
