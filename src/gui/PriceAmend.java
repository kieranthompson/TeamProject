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

public class PriceAmend {

    private static JFrame frame;
    private static JTextField cash;
    private static JLabel lblpercent;
    private static JLabel amendReason;
    private static JLabel lblcash;
    private static JButton comboBtn;
    private static JComboBox comboBox;
    public static AdminLogin window;
    private static boolean flag =false;

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

        frame = new JFrame();
        frame.setTitle("Price Amendment");
        frame.setBounds(400, 600, 200, 600);
        frame.getContentPane().setLayout(null);

        JLabel amendReason = new JLabel("Reason for amendment:");
        amendReason.setBounds(105, 5, 160, 40);
        amendReason.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        frame.getContentPane().add(amendReason);

        JLabel lblcash = new JLabel("Amendment Amount");
        lblcash.setBounds(110, 105, 160, 55);
        lblcash.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        frame.getContentPane().add(lblcash);

        cash = new JTextField();
        cash.setBounds(105, 145 , 170, 30);
        frame.getContentPane().add(cash);

        JButton btnPAmend = new JButton("Amend by percent");
        btnPAmend.setBounds(100, 180, 180, 45);
        btnPAmend.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        frame.getContentPane().add(btnPAmend);

        JButton btnCAmend = new JButton("Amend by cash");
        btnCAmend.setBounds(100, 230, 180, 45);
        btnCAmend.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        frame.getContentPane().add(btnCAmend);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(100, 35, 200, 30);
        comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        frame.getContentPane().add(comboBox);

        comboBox.addItem("Promotional Offer");
        comboBox.addItem("Manager Discretion");
        comboBox.addItem("Price Promise");


        btnPAmend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String) comboBox.getSelectedItem();
                if (choice.equalsIgnoreCase("Manager discretion") || choice.equalsIgnoreCase("Price promise")) {
                    window = new AdminLogin();

                    if (GeneralWindow.totalPrice > 0) {
                        if (flag) {
                            System.out.println("Discount Already Applied");
                            window.frame.dispose();

                        } while(!flag && window.isLoggedIn) {
                            System.out.println(GeneralWindow.totalPrice);
                            GeneralWindow.totalPrice = GeneralWindow.totalPrice - (GeneralWindow.totalPrice * ((Double.parseDouble(cash.getText()) / 100)));
                            System.out.println(GeneralWindow.totalPrice);
                            GeneralWindow.totalLabel.setText("total: " + GeneralWindow.totalPrice);
                            frame.dispose();
                            flag = true;
                            window.frame.dispose();

                        }
                    }

                }
                else if(!flag && choice.equalsIgnoreCase("Promotional Offer")){

                    System.out.println(GeneralWindow.totalPrice);
                    GeneralWindow.totalPrice = GeneralWindow.totalPrice - (GeneralWindow.totalPrice * ((Double.parseDouble(cash.getText()) / 100)));
                    System.out.println(GeneralWindow.totalPrice);
                    flag = true;
                    frame.dispose();
                }


            }


        });

        btnCAmend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String) comboBox.getSelectedItem();
                if (choice.equalsIgnoreCase("Manager discretion") || choice.equalsIgnoreCase("Price promise")) {
                    window = new AdminLogin();

                    if (GeneralWindow.totalPrice > 0) {
                        if (flag) {
                            System.out.println("Discount Already Applied");
                            window.frame.dispose();

                        } while(!flag && window.isLoggedIn == true) {
                            System.out.println(GeneralWindow.totalPrice);
                            GeneralWindow.totalPrice = GeneralWindow.totalPrice - Double.parseDouble(cash.getText());
                            System.out.println(GeneralWindow.totalPrice);
                            flag = true;
                            window.frame.dispose();
                        }
                    }

                }
                else if(!flag && choice.equalsIgnoreCase("Promotional Offer")){

                    System.out.println(GeneralWindow.totalPrice);
                    GeneralWindow.totalPrice = GeneralWindow.totalPrice - Double.parseDouble(cash.getText());
                    System.out.println(GeneralWindow.totalPrice);
                    flag = true;
                    frame.dispose();
                }


            }

        });

        //JFrame display and size
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public String log(String s) {
        System.out.println(s);
        return s;
    }
}