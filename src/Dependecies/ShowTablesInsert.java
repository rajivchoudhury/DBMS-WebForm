package Dependecies;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ShowTablesInsert implements ActionListener{
    JFrame frame;
    JLabel heading;
    JButton userTable, cartTable, orderTable, paymentTable, closeButton, addressTable, itemTable;

    public ShowTablesInsert(){
        frame = new JFrame("Show Tables");

        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 700,500);
        frame.add(imageLabel);

        heading = new JLabel("Tables");
        heading.setFont(new Font("serif", Font.BOLD, 25));
        heading.setBounds(220, 20, 200, 40);
        heading.setForeground(Color.BLACK);
        imageLabel.add(heading);

        userTable = new JButton("User");
        userTable.setBounds(50, 80, 200, 40);
        userTable.setFont(new Font("serif",Font.BOLD,15));
        userTable.addActionListener(this);
        imageLabel.add(userTable);
        
        cartTable = new JButton("Shopping Cart");
        cartTable.setBounds(260, 80, 200, 40);
        cartTable.setFont(new Font("serif",Font.BOLD,15));
        cartTable.addActionListener(this);
        imageLabel.add(cartTable);
        
        orderTable = new JButton("Order");
        orderTable.setBounds(50, 140, 200, 40);
        orderTable.setFont(new Font("serif",Font.BOLD,15));
        orderTable.addActionListener(this);
        imageLabel.add(orderTable);

        paymentTable = new JButton("Payment Details");
        paymentTable.setBounds(260, 140, 200, 40);
        paymentTable.setFont(new Font("serif",Font.BOLD,15));
        paymentTable.addActionListener(this);
        imageLabel.add(paymentTable);

        closeButton = new JButton("Back To HomePage");
        closeButton.setBounds(50, 260, 410, 40);
        closeButton.setFont(new Font("serif",Font.BOLD,15));
        closeButton.addActionListener(this);
        imageLabel.add(closeButton);
        
        addressTable = new JButton("Address");
        addressTable.setBounds(50, 200, 200, 40);
        addressTable.setFont(new Font("serif",Font.BOLD,15));
        addressTable.addActionListener(this);
        imageLabel.add(addressTable);

        itemTable = new JButton("Item");
        itemTable.setBounds(260, 200, 200, 40);
        itemTable.setFont(new Font("serif",Font.BOLD,15));
        itemTable.addActionListener(this);
        imageLabel.add(itemTable);

        frame.setVisible(true);
        frame.setSize(700, 500);
        frame.setLocation(200, 80);
    }

    public static void main(String[] args) {
        new ShowTablesInsert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userTable){
            frame.setVisible(false);
            new Dependecies.User.AddUser();
        }
        if(e.getSource() == cartTable){
            // frame.setVisible(false);
            // new ViewUser();
        }
        if(e.getSource() == orderTable){
            // frame.setVisible(false);
            // new RemoveUser();
        }
        if(e.getSource() == paymentTable){
            // frame.setVisible(false);
            // new UpdateUser();
        }
        if(e.getSource() == closeButton){
            frame.setVisible(false);
            new HomePage();
        }
        if(e.getSource() == addressTable){
            // frame.setVisible(false);
            // new UpdateUser();
        }
        if(e.getSource() == itemTable){
            // frame.setVisible(false);
            // new UpdateUser();
        }
    }
}
