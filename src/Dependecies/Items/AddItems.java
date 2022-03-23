package Dependecies.Items;

import java.awt.*;
import javax.swing.*;

import Dependecies.ConnectionToDB;
import Dependecies.HomePage;
import Dependecies.ShowTablesInsert;

import java.awt.event.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddItems  implements ActionListener{
    JFrame frame;
    JLabel imageLabel;
    JTextField cartIDField, nameField, userIDField, phoneField;
    JButton submitButton, cancelButton;

    public AddItems(){
        frame = new JFrame("Add Cart");

        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 900, 700);
        frame.add(imageLabel);

        JLabel heading = new JLabel("New Cart Details");
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("serif", Font.ITALIC, 25));
        heading.setBounds(340, 30, 300, 30);
        imageLabel.add(heading);

        JLabel label = new JLabel("Cart ID: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 100, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Name : ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 150, 120, 30);
        imageLabel.add(label);

        label = new JLabel("User ID : ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 200, 120, 30);
        imageLabel.add(label);

        cartIDField = new JTextField();
        cartIDField.setBounds(180, 100, 200, 30);
        cartIDField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(cartIDField);

        nameField = new JTextField();
        nameField.setBounds(180, 150, 200, 30);
        nameField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(nameField);

        userIDField = new JTextField();
        userIDField.setBounds(180, 200, 200, 30);
        userIDField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(userIDField);

        submitButton = new JButton("Submit");
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBounds(300, 500, 100, 50);
        submitButton.addActionListener(this);
        imageLabel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);
        cancelButton.setBounds(430, 500, 100, 50);
        cancelButton.addActionListener(this);
        imageLabel.add(cancelButton);

        frame.setVisible(true);
        frame.setSize(900, 700);
        frame.setLocation(50, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton){
            frame.setVisible(false);
            new ShowTablesInsert();
        }
        if(e.getSource() == submitButton){
            try{
                ConnectionToDB connect = new ConnectionToDB();
                String query = "insert into ShoppingCart values ("+cartIDField.getText()+",'"+nameField.getText()+"',"+userIDField.getText()+")";
                try{
                    connect.statement.executeUpdate(query);
                }
                catch(SQLIntegrityConstraintViolationException ev){
                    if(ev.getLocalizedMessage().equals("Duplicate entry '"+cartIDField.getText()+"' for key 'shoppingcart.PRIMARY'")){
                        JOptionPane.showMessageDialog(null, "Adding Duplicate CART");
                        frame.setVisible(false);
                        new AddItems();
                    }
                }
                JOptionPane.showMessageDialog(null, "Succesfully Added");
                frame.setVisible(false);
                new HomePage();
            }
            catch(Exception ev){
                ev.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Adding User");
                frame.setVisible(false);
                new AddItems();
            }
        }
    }
    
    public static void main(String[] args) {
        new AddItems();
    }
}
