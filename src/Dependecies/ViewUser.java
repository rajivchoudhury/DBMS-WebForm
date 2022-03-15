package Dependecies;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ViewUser implements ActionListener{
    JFrame frame;
    JLabel imageLabel;
    JTextField inputField;
    JButton submitButton, cancelButton;

    public ViewUser(){
        frame = new JFrame("View User");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 500, 270);
        frame.add(imageLabel);

        JLabel label = new JLabel("USER ID");
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
                    new PrintUser(rSet);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Employee ID");
                    new ViewUser();
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
        new ViewUser();
    }
}
