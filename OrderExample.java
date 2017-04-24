package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class OrderExample {
	public static void main(String[] args){
		
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
		
		
		JFrame frame = new JFrame();
		JTable table= new JTable();
		table.setBounds(428, 445, 396, -328);
		
		Object[] columns = {"Product Id", "Product Name", "Description", "Price"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table.setRowHeight(30);
		
		JTextField prodId = new JTextField();
		JTextField prodName = new JTextField();
		JTextField prodPrice = new JTextField();
		
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnExit = new JButton("Exit");
		
		prodId.setBounds(20, 56, 230, 38);
		prodName.setBounds(20, 129, 230, 38);
		prodPrice.setBounds(21, 201, 229, 39);
		
		btnAdd.setBounds(20, 451, 100, 38);
		btnDelete.setBounds(150, 451, 100, 38);
		btnExit.setBounds(20, 511, 100, 39);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(281, 32, 440, 518);
		
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(pane);
		
		frame.getContentPane().add(prodId);
		frame.getContentPane().add(prodName);
		frame.getContentPane().add(prodPrice);
		
		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnExit);
		
		JLabel lblAddId = new JLabel("Product ID");
		lblAddId.setBounds(20, 32, 79, 14);
		frame.getContentPane().add(lblAddId);
		
		JLabel lblProduct = new JLabel("Product Name");
		lblProduct.setBounds(20, 105, 100, 14);
		frame.getContentPane().add(lblProduct);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(20, 178, 90, 14);
		frame.getContentPane().add(lblProductPrice);
		
		JButton btnListProducts = new JButton("List Products");
		btnListProducts.setBounds(150, 511, 100, 39);
		frame.getContentPane().add(btnListProducts);
		
		JTextArea description = new JTextArea();
		description.setBounds(20, 276, 230, 164);
		frame.getContentPane().add(description);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(20, 251, 116, 14);
		frame.getContentPane().add(lblDescription);
		
		Object[] row = new Object[4];
		
		btnAdd.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				row[0] = prodId.getText();
				row[1] = prodName.getText();
				row[2] = prodPrice.getText();
				
				model.addRow(row);
				try{
				Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement("insert into stock(sname, description, price) values(?,?,?);");
				ps.setString(1,  prodName.getText());
				ps.setString(2,  description.getText());
				ps.setDouble(3, Double.parseDouble( prodPrice.getText()));
				ps.executeUpdate();
				}
				catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
				if(i >= 0){
					model.removeRow(i);
				}
				else{
					System.out.print("Delete ");
					
					
				}
			}
			
		});
		
		table.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				prodId.setText(model.getValueAt(i, 0). toString());
				prodName.setText(model.getValueAt(i, 1).toString());
				prodPrice.setText(model.getValueAt(i, 3).toString());
				description.setText(model.getValueAt(i,  2).toString());
			}
		});
		
		btnListProducts.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int i = table.getSelectedRow();
				try{
				Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs;
				if(prodId.getText().equalsIgnoreCase("")) 
					rs = statement.executeQuery("select * from stock;");
				
				else
					rs = statement.executeQuery("select * from stock where stockID = " + prodId.getText() + ";");
				
				while(rs.next()){
					row[0] = rs.getInt("stockID");
					row[1] = rs.getString("sname");
					row[2] = rs.getString("description");
					row[3] = rs.getDouble("price");
					
					model.addRow(row);
				}
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		btnExit.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				
			}
		});
		frame.setSize(771, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
