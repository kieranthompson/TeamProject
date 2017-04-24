package gui;
import database.DBConnection;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.GregorianCalendar;

@SuppressWarnings("all")
public class GeneralWindow extends JFrame {
	private String selectedItem;
	private JFrame frame;
	public static double totalPrice = 0;
	public static JTextField productCodeField;
	public static DefaultTableModel model;
	public static int transactionID = 0;
	public static JLabel currentEmployee;
	public static JLabel totalLabel, timeLabel;
	public static String receiptDetails = "";
	public static boolean check = false;
	static String OrderDetails = "";

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
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralWindow window = new GeneralWindow();
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
	public GeneralWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	static JButton btnEnter = new JButton("Enter");
	private void initialize() {

		frame = new JFrame();
		this.setBounds(100, 100, 850, 648);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel ItemLabel = new JLabel("ITEM");
		ItemLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		ItemLabel.setBounds(10, 10, 40, 20);
		this.getContentPane().add(ItemLabel);

		JLabel PriceLabel = new JLabel("PRICE");
		PriceLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		PriceLabel.setBounds(315, 10, 40, 20);
		this.getContentPane().add(PriceLabel);

		JButton btnStock = new JButton("Stock");
		btnStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStock.setBounds(668, 91, 156, 42);
		this.getContentPane().add(btnStock);

		JButton btnStaff = new JButton("Employees");
		btnStaff.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStaff.setBounds(668, 175, 156, 42);
		this.getContentPane().add(btnStaff);

		JButton btnPriceAmed = new JButton("Price Amend");
		btnPriceAmed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPriceAmed.setBounds(668, 255, 156, 42);
		this.getContentPane().add(btnPriceAmed);

		JButton btnTotal = new JButton("Total");
		btnTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTotal.setBounds(668, 508, 156, 42);
		this.getContentPane().add(btnTotal);

		productCodeField = new JTextField();
		productCodeField.setBounds(10, 525, 601, 42);
		this.getContentPane().add(productCodeField);
		productCodeField.setColumns(10);
		productCodeField.requestFocusInWindow();

		JLabel lblProductcodeBar = new JLabel("Product Code");
		lblProductcodeBar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductcodeBar.setBounds(10, 500, 177, 22);
		this.getContentPane().add(lblProductcodeBar);

		totalLabel = new JLabel("Total: ");
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		totalLabel.setBounds(480, 500, 177, 22);
		this.getContentPane().add(totalLabel);

		timeLabel = new JLabel("Time: ");
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeLabel.setBounds(675, 600, 177, 22);
		this.getContentPane().add(timeLabel);


		btnEnter.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		btnEnter.setBounds(723, 422, 89, 42);
		this.getContentPane().add(btnEnter);
		btnEnter.setVisible(false);
		frame.getRootPane().setDefaultButton(btnEnter);

		JTable table = new JTable();
		Object[] identifiers = {"Item", "Price"};
		table.setBounds(10, 30, 601, 453);
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(identifiers);
		table.setModel(model);
		this.getContentPane().add(table);


		this.getRootPane().setDefaultButton(btnEnter);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setRowHeight(30);


		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(668, 12, 156, 42);
		getContentPane().add(btnLogout);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(668, 340, 156, 42);
		getContentPane().add(btnDelete);

		JButton btnReturns = new JButton("Returns");
		btnReturns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturns.setBounds(668, 422, 158, 42);
		getContentPane().add(btnReturns);

		currentEmployee = new JLabel("");
		currentEmployee.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		currentEmployee.setBounds(10, 600, 300, 25);
		getContentPane().add(currentEmployee);

        productCodeField.requestFocusInWindow();

		checkStock();
		currentDate();

		btnStock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderExample window = new OrderExample();
				window.initialize();
			}
		});

		btnPriceAmed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PriceAmend window = new PriceAmend();
				window.initialize();
			}
		});

		btnStaff.addActionListener(e -> {
            staff window = new staff();
            window.setVisible(true);
        });
		
		btnReturns.addActionListener(e -> {
            Returns window = new Returns();
            window.initialize();
        });

		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DBConnection.getConnection();
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("select * from stock where stockID = " + Integer.valueOf(productCodeField.getText()) +";");

					PreparedStatement ps = connection.prepareStatement("update stock set quantity = quantity - 1 where stockID = ?;");
					ps.setInt(1, Integer.valueOf(productCodeField.getText()));
					ps.executeUpdate();

					productCodeField.requestFocusInWindow();
					Object[] row = new Object[2];

					if(rs.next()) {

                        row[0] = rs.getString("sname");
                        row[1] = rs.getDouble("price");
						receiptDetails += "\n " + rs.getInt("stockID") + "\t" + row[0].toString() + "\t " + row[1].toString();
                        model.addRow(row);


						totalPrice = totalPrice + (double) model.getValueAt(table.getRowCount() - 1, 1);
                        DecimalFormat df = new DecimalFormat("#.##");
                        totalPrice = Double.valueOf(df.format(totalPrice));
						System.out.println("price: " + totalPrice);
						totalLabel.setText("total: " + totalPrice);
					}
					else
						JOptionPane.showMessageDialog(null, "item doesn't exist");
				} catch(SQLException sqle) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
				productCodeField.setText("");
				productCodeField.requestFocusInWindow();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DBConnection.getConnection();
					Statement statement = connection.createStatement();

					int i = table.getSelectedRow();

					if (i >= 0) {
						selectedItem = String.valueOf(model.getValueAt(table.getSelectedRow(), 0));
						totalPrice -= (double) model.getValueAt(table.getSelectedRow(), 1);

						PreparedStatement ps = connection.prepareStatement("update stock set quantity = quantity + 1 where sname = ?;");
						ps.setString(1, selectedItem);
						ps.executeUpdate();

						model.removeRow(i);
						totalLabel.setText("Total: €" + totalPrice);
						System.out.println(totalPrice);
					} else
						System.out.println("delete error");


				}catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
        });

		btnTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(totalPrice > 0) {
					int reply = JOptionPane.showConfirmDialog(null, totalPrice);
					if (reply == JOptionPane.YES_OPTION) {
						Connection connection = DBConnection.getConnection();

						try {
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery("select * from receipt order by transactionID desc limit 1;");
                            while(rs.next()) {
                                transactionID = rs.getInt("transactionID") + 1;
								try {
									File newTextFile = new File("C:\\Users\\Kieran\\Desktop\\TeamProject\\receipts/receipt" + transactionID + ".txt");
									FileWriter fw = new FileWriter(newTextFile);
									fw.write(receiptDetails);
									fw.close();
								} catch(IOException ioe) {
									ioe.printStackTrace();
								}
                            }


							for(int i = 0; i < model.getRowCount(); i++){

								PreparedStatement ps = connection.prepareStatement("insert into receipt (itemName, ItemPrice, transactionID) values (?, ?, ?);");
								String string = String.valueOf(model.getValueAt(i, 1));
								ps.setString(1, String.valueOf(model.getValueAt(i, 0)));
								ps.setDouble(2, Double.parseDouble(string));
								ps.setInt(3, transactionID);
								ps.executeUpdate();

							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}


						CustomersWindow window = new CustomersWindow();
						window.setVisible(true);

						totalPrice = 0;
						log("" + totalPrice);
					} else {
						log("void");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Empty Transaction");
				}
			}
		});

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login window = new Login();
				window.run();
			}
		});
	}

    private String log(String value) {
        System.out.println("" + value);
        return value;
    }

    public void currentDate() {

		Thread clock = new Thread() {
			public void run() {
				for ( ; ; ) {

					Calendar cal = new GregorianCalendar();
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR_OF_DAY);

					String secondstr = String.valueOf(second);
					String minutestr = String.valueOf(minute);
					String hourstr = String.valueOf(hour);
					if(second < 10)
						secondstr = "0" + String.valueOf(second);

					if(minute < 10)
						minutestr = "0" + String.valueOf(minute);

					if(hour < 10)
						hourstr = "0" + String.valueOf(hour);

					timeLabel.setText("Time: " + hourstr + ":" + minutestr + ":" + secondstr);
					if(hour == 18 && minute == 9 && second == 0) {
						checkStock();
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		clock.start();
	}

    private void checkStock() {

		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		try {
			File newFile = new File("C:\\Users\\Kieran\\Desktop\\TeamProject\\OrderReceipts/OrderReceipt" + day + "_" + month + "_" + year + ".txt");
			FileWriter fw = new FileWriter(newFile);
			fw.write("Order Receipt For " + day + "/" + month + "/" + year + "\n" + OrderDetails);
			fw.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from stock where quantity < 10;");

			while(resultSet.next()) {
				Statement statement1 = connection.createStatement();
				statement1.executeUpdate("update stock set quantity = quantity + 10 where quantity <= 0;");
				OrderDetails += "\n " + resultSet.getString("sname") + " updated to \t" + (resultSet.getInt("quantity") + 10);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}