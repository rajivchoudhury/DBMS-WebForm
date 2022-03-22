package Dependecies.User;

import java.io.FileWriter;
import java.sql.*;

import javax.swing.JOptionPane;

import Dependecies.ConnectionToDB;

public class ExportUser {
    
    public ExportUser(){
        ConnectionToDB connect = new ConnectionToDB();
            try {
                FileWriter fr = new FileWriter("output.csv");
                ResultSet rSet = connect.statement.executeQuery("SELECT * FROM User");
                String s1 = "userID, name, email, phone\n";
                fr.write(s1);
                while(rSet.next()){
                    String str = "";
                    str += rSet.getInt("userID") + ", ";
                    str += rSet.getString("name") + ", ";
                    str += rSet.getString("email") + ", ";
                    str += rSet.getString("phone") + "\n";
                    fr.write(str, 0, str.length());
                }
                fr.close();
                JOptionPane.showMessageDialog(null, "Data Exported succesfully");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
    }
}
