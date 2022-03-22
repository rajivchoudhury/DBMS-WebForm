package Dependecies.User;

import java.awt.*;
import javax.swing.*;

import Dependecies.ConnectionToDB;
import Dependecies.HomePage;
import Dependecies.ShowTablesUpdate;

import java.awt.event.*;
import java.sql.*;

class SearchUser implements ActionListener{
    JFrame frame;
    JLabel imageLabel;
    JTextField userIDField, nameField, emailField, phoneField;
    JButton submitButton, cancelButton;

    public SearchUser(ResultSet rSet){
        frame = new JFrame("Update User");

        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 900, 700);
        frame.add(imageLabel);

        JLabel heading = new JLabel("New User Details");
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("serif", Font.ITALIC, 25));
        heading.setBounds(340, 30, 300, 30);
        imageLabel.add(heading);

        JLabel label = new JLabel("User ID: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 100, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Name: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 150, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Email: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 200, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Phone: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 250, 120, 30);
        imageLabel.add(label);

        try{
            userIDField = new JTextField(rSet.getString("userID"));
            userIDField.setBounds(180, 100, 200, 30);
            userIDField.setFont(new Font("sans-serif", Font.PLAIN, 18));
            imageLabel.add(userIDField);

            nameField = new JTextField(rSet.getString("name"));
            nameField.setBounds(180, 150, 200, 30);
            nameField.setFont(new Font("sans-serif", Font.PLAIN, 18));
            imageLabel.add(nameField);

            emailField = new JTextField(rSet.getString("email"));
            emailField.setBounds(180, 200, 200, 30);
            emailField.setFont(new Font("sans-serif", Font.PLAIN, 18));
            imageLabel.add(emailField);

            phoneField = new JTextField(rSet.getString("phone"));
            phoneField.setBounds(180, 250, 200, 30);
            phoneField.setFont(new Font("sans-serif", Font.PLAIN, 18));
            imageLabel.add(phoneField);
        }
        catch(Exception ev){
            ev.printStackTrace();
        }
        
        submitButton = new JButton("Update");
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
            new ShowTablesUpdate();
        }
        if(e.getSource() == submitButton){
            try{
                ConnectionToDB connect = new ConnectionToDB();
                String query = "update user set name='"+nameField.getText()+"', email='"+emailField.getText()+"', phone="+phoneField.getText()+" where userId="+userIDField.getText();
                System.out.println(query);
                connect.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Succesfully Updated");
                frame.setVisible(false);
                new HomePage();
            }
            catch(Exception ev){
                ev.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Updating User");
                frame.setVisible(false);
                ConnectionToDB connect = new ConnectionToDB();
                try {
                    new SearchUser(connect.statement.executeQuery("select * from user where userID="+userIDField.getText()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

public class UpdateUser implements ActionListener{
    JFrame frame;
    JLabel imageLabel;
    JTextField inputField;
    JButton submitButton, cancelButton;

    public UpdateUser(){
        frame = new JFrame("Search User");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 500, 270);
        frame.add(imageLabel);

        JLabel label = new JLabel("User ID");
        label.setFont(new Font("sans-serif", Font.BOLD, 30));
        label.setBounds(10, 20, 250, 30);
        imageLabel.add(label);

        inputField = new JTextField();
        inputField.setBounds(270, 20, 200, 30);
        imageLabel.add(inputField);

        submitButton = new JButton("Search");
        submitButton.addActionListener(this);
        submitButton.setBounds(270, 170, 80, 30);
        imageLabel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(390, 170, 80, 30);
        imageLabel.add(cancelButton);

        frame.setVisible(true);
        frame.setSize(500, 270);
        frame.setLocation(250, 260);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            String empId = inputField.getText();
            ResultSet rSet;
            try{
                ConnectionToDB connect = new ConnectionToDB();
                rSet = connect.statement.executeQuery("select * from user where userID="+empId);
                if(rSet.next()){
                    frame.setVisible(false);
                    new SearchUser(rSet);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Employee ID");
                    new UpdateUser();
                }
            }
            catch(Exception ev){
                ev.printStackTrace();
            }
        }
        if(e.getSource() == cancelButton){
            frame.setVisible(false);
            new HomePage();
        }
    }

    public static void main(String[] args) {
        new UpdateUser();
    }
}
