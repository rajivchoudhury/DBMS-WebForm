package Dependecies;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

public class AddUser implements ActionListener{
    JFrame frame;
    JLabel imageLabel;
    JTextField userIDField, nameField, emailField, phoneField;
    JButton submitButton, cancelButton;

    public AddUser(){
        frame = new JFrame("Add Employee");

        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 900, 700);
        frame.add(imageLabel);

        JLabel heading = new JLabel("New Employee Details");
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("serif", Font.ITALIC, 25));
        heading.setBounds(340, 30, 300, 30);
        imageLabel.add(heading);

        JLabel label = new JLabel("User ID: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 100, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Name : ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 150, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Email : ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 200, 120, 30);
        imageLabel.add(label);

        label = new JLabel("Phone: ");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setBounds(50, 250, 120, 30);
        imageLabel.add(label);

        userIDField = new JTextField();
        userIDField.setBounds(180, 100, 200, 30);
        userIDField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(userIDField);

        nameField = new JTextField();
        nameField.setBounds(180, 150, 200, 30);
        nameField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(nameField);

        emailField = new JTextField();
        emailField.setBounds(180, 200, 200, 30);
        emailField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(emailField);

        phoneField = new JTextField();
        phoneField.setBounds(180, 250, 200, 30);
        phoneField.setFont(new Font("sans-serif", Font.PLAIN, 18));
        imageLabel.add(phoneField);

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
            new HomePage();
        }
        if(e.getSource() == submitButton){
            try{
                ConnectionToDB connect = new ConnectionToDB();
                String query = "insert into User values ("+userIDField.getText()+",'"+nameField.getText()+"','"+emailField.getText()+"',"+phoneField.getText()+")";
                try{
                    connect.statement.executeUpdate("create table user (userID integer primary key, name varchar(45), email varchar(60), phone bigint)");
                    connect.statement.executeUpdate(query);
                }
                catch(SQLSyntaxErrorException ev){
                    if(ev.getLocalizedMessage().equals("Table 'user' already exists")){
                        connect.statement.executeUpdate(query);
                    }
                }
                catch(SQLIntegrityConstraintViolationException ev){
                    if(ev.getLocalizedMessage().equals("Duplicate entry '"+userIDField.getText()+"' for key 'user.PRIMARY'")){
                        JOptionPane.showMessageDialog(null, "Adding Duplicate USER");
                        frame.setVisible(false);
                        new AddUser();
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
                new AddUser();
            }
        }
    }
    
    public static void main(String[] args) {
        new AddUser();
    }    
}
