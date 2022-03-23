package Dependecies.ShoppingCart;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Dependecies.ConnectionToDB;

public class ImportCart {
    public ImportCart(){
        ConnectionToDB connect = new ConnectionToDB();
            try {
                connect.connection.setAutoCommit(false);
                File inpFile = new File("src/Dependecies/ShoppingCart/data.csv");
                String query = "insert into shoppingCart (cartID, name, userID) values (?,?,?)";
                PreparedStatement statement = connect.connection.prepareStatement(query);
                Scanner scanner = new Scanner(inpFile);
                while(scanner.hasNextLine()){
                    String str = scanner.nextLine();
                    String []s = str.split(",");
                    System.out.println(query);
                    Integer userID = Integer.parseInt(s[0]);
                    statement.setInt(1, userID);
                    statement.setString(2, s[1]);
                    statement.setInt(3, Integer.parseInt(s[2]));
                    statement.addBatch();
                }
                statement.executeBatch();
                connect.connection.commit();
                connect.connection.close();
                scanner.close();
                JOptionPane.showMessageDialog(null, "Data Inserted succesfully");
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Importing Data");
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
    }
}
