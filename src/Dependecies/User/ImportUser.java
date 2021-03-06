package Dependecies.User;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Dependecies.ConnectionToDB;

public class ImportUser {
    
    public ImportUser(){
        ConnectionToDB connect = new ConnectionToDB();
            try {
                connect.connection.setAutoCommit(false);
                File inpFile = new File("src/Dependecies/User/data1.csv");
                String query = "insert into User (userID, name, email, phone) values (?,?,?,?)";
                PreparedStatement statement = connect.connection.prepareStatement(query);
                Scanner scanner = new Scanner(inpFile);
                while(scanner.hasNextLine()){
                    String str = scanner.nextLine();
                    String []s = str.split(",");
                    System.out.println(query);
                    System.out.println(s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
                    int userID = Integer.parseInt(s[0]);
                    statement.setInt(1, userID);
                    statement.setString(2, s[1]);
                    statement.setString(3, s[2]);
                    statement.setInt(4, Integer.parseInt(s[3]));
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
