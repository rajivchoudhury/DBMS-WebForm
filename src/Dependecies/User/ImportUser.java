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
                File inpFile = new File("src/Dependecies/User/data1.csv");
                Scanner scanner = new Scanner(inpFile);
                while(scanner.hasNextLine()){
                    String str = scanner.nextLine();
                    String []s = str.split(",");
                    String query = "insert into User (userID, name, email, phone) values ("+ s[0] + ",'"+s[1]+"','"+s[2]+"',"+s[3]+")";
                    System.out.println(query);
                    connect.statement.executeUpdate(query);
                }
                scanner.close();
                JOptionPane.showMessageDialog(null, "Data Inserted succesfully");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
    }
}
