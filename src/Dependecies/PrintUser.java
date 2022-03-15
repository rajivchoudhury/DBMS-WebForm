package Dependecies;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class PrintUser implements ActionListener{
    JFrame frame;
    JLabel label, imageLabel;
    JButton newButton, closeButton;

    public PrintUser(ResultSet rSet){
        frame = new JFrame("Employee Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 595, 642);
        frame.add(imageLabel);

        label = new JLabel("USER Details");
        label.setFont(new Font("serif", Font.BOLD, 25));
        label.setBounds(80, 20, 250, 40);
        imageLabel.add(label);

        label = new JLabel("User ID: ");
        label.setFont(new Font("serif", Font.BOLD, 17));
        label.setBounds(20, 70, 130, 30);
        imageLabel.add(label);

        label = new JLabel("Name: ");
        label.setFont(new Font("serif", Font.BOLD, 17));
        label.setBounds(20, 110, 100, 30);
        imageLabel.add(label);

        label = new JLabel("Email: ");
        label.setFont(new Font("serif", Font.BOLD, 17));
        label.setBounds(20, 150, 130, 30);
        imageLabel.add(label);

        label = new JLabel("Phone: ");
        label.setFont(new Font("serif", Font.BOLD, 17));
        label.setBounds(20, 190, 100, 30);
        imageLabel.add(label);
        
        try{
            label = new JLabel(rSet.getString("userID"));
            label.setFont(new Font("serif", Font.PLAIN, 15));
            label.setBounds(160, 70, 200, 30);
            imageLabel.add(label);

            label = new JLabel(rSet.getString("name"));
            label.setFont(new Font("serif", Font.PLAIN, 15));
            label.setBounds(160, 110, 200, 30);
            imageLabel.add(label);

            label = new JLabel(rSet.getString("email"));
            label.setFont(new Font("serif", Font.PLAIN, 15));
            label.setBounds(160, 150, 200, 30);
            imageLabel.add(label);

            label = new JLabel(rSet.getString("phone"));
            label.setFont(new Font("serif", Font.PLAIN, 15));
            label.setBounds(160, 190, 200, 30);
            imageLabel.add(label);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        newButton = new JButton("New Search");
        newButton.setForeground(Color.WHITE);
        newButton.setBackground(Color.BLACK);
        newButton.setOpaque(true);
        newButton.setBorderPainted(false);
        newButton.addActionListener(this);
        newButton.setBounds(70, 500, 160, 30);
        imageLabel.add(newButton);

        closeButton = new JButton("Close");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.BLACK);
        closeButton.setOpaque(true);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(this);
        closeButton.setBounds(280, 500, 160, 30);
        imageLabel.add(closeButton);

        frame.setVisible(true);
        frame.setSize(595, 642);
        frame.setLocation(200, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newButton){
            frame.setVisible(false);
            new ViewUser();
        }
        if(e.getSource() == closeButton){
            System.exit(0);
        }
    }
}
