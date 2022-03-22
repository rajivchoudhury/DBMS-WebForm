package Dependecies.User;

import java.awt.*;
import javax.swing.*;

import Dependecies.ConnectionToDB;
import Dependecies.HomePage;
import Dependecies.ShowTablesRemove;

import java.awt.event.*;
import java.sql.*;

public class RemoveUser implements ActionListener{
    JFrame frame;
    JLabel imageLabel, empNameLabel, mobileLabel, emailLabel, empName, empNumber, empEmail;
    JTextField inputField;
    JButton searchButton, backButton, removeButton, cancelButton;

    public RemoveUser(){
        frame = new JFrame("View User");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 500, 500);
        frame.add(imageLabel);

        JLabel label = new JLabel("Employee ID");
        label.setFont(new Font("sans-serif", Font.BOLD, 30));
        label.setBounds(20, 20, 240, 30);
        imageLabel.add(label);

        inputField = new JTextField();
        inputField.setBounds(260, 20, 200, 30);
        imageLabel.add(inputField);

        empNameLabel = new JLabel("Name: ");
        empNameLabel.setFont(new Font("serif", Font.BOLD, 20));
        empNameLabel.setBounds(20, 120, 120, 30);
        imageLabel.add(empNameLabel);

        mobileLabel = new JLabel("Mobile No: ");
        mobileLabel.setFont(new Font("serif", Font.BOLD, 20));
        mobileLabel.setBounds(20, 170, 120, 30);
        imageLabel.add(mobileLabel);

        emailLabel = new JLabel("Email ID: ");
        emailLabel.setFont(new Font("serif", Font.BOLD, 20));
        emailLabel.setBounds(20, 220, 120, 30);
        imageLabel.add(emailLabel);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setBounds(260, 70, 80, 30);
        imageLabel.add(searchButton);

        backButton = new JButton("Cancel");
        backButton.addActionListener(this);
        backButton.setBounds(380, 70, 80, 30);
        imageLabel.add(backButton);
        
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        removeButton.setBounds(50, 280, 100, 30);
        imageLabel.add(removeButton);

        cancelButton = new JButton("cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(170, 280, 100, 30);
        imageLabel.add(cancelButton);

        empNameLabel.setVisible(false);
        mobileLabel.setVisible(false);
        emailLabel.setVisible(false);
        removeButton.setVisible(false);
        cancelButton.setVisible(false);

        frame.setVisible(true);
        frame.setSize(500, 550);
        frame.setLocation(250, 160);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            frame.setVisible(false);
            new ShowTablesRemove();
        }
        if(e.getSource() == searchButton){
            try{
                ConnectionToDB connect = new ConnectionToDB();
                String sql = "select * from user where userID=" + inputField.getText();
                ResultSet rSet = connect.statement.executeQuery(sql);
                if(!rSet.next()){
                    JOptionPane.showMessageDialog(null, "Employee not found");
                    frame.setVisible(false);
                    new RemoveUser();
                }
                else{
                    empName = new JLabel(rSet.getString("name"));
                    empName.setFont(new Font("serif", Font.PLAIN, 18));
                    empName.setBounds(150, 120, 180, 30);
                    imageLabel.add(empName);

                    empNumber = new JLabel(rSet.getString("phone"));
                    empNumber.setFont(new Font("serif", Font.PLAIN, 18));
                    empNumber.setBounds(150, 170, 180, 30);
                    imageLabel.add(empNumber);

                    empEmail = new JLabel(rSet.getString("email"));
                    empEmail.setFont(new Font("serif", Font.PLAIN, 18));
                    empEmail.setBounds(150, 220, 180, 30);
                    imageLabel.add(empEmail);

                    empNameLabel.setVisible(true);
                    mobileLabel.setVisible(true);
                    emailLabel.setVisible(true);
                    removeButton.setVisible(true);
                    cancelButton.setVisible(true);
                }
            }
            catch(Exception ev){
                ev.printStackTrace();
            }
        }
        if(e.getSource() == cancelButton){
            empNameLabel.setVisible(false);
            mobileLabel.setVisible(false);
            emailLabel.setVisible(false);
            removeButton.setVisible(false);
            cancelButton.setVisible(false);
            empName.setVisible(false);
            empEmail.setVisible(false);
            empNumber.setVisible(false);
        }
        if(e.getSource() == removeButton){
            try{
                String sql = "delete from user where userID="+inputField.getText();
                ConnectionToDB connect = new ConnectionToDB();
                int flag = connect.statement.executeUpdate(sql);
                if(flag >= 1){
                    JOptionPane.showMessageDialog(null, "Data Removed Succesfully");
                    frame.setVisible(false);
                    new HomePage();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error Deleting message");
                }
            }
            catch(Exception ec){
                ec.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new RemoveUser();
    }
}
