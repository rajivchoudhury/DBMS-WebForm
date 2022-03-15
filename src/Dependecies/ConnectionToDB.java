package Dependecies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionToDB {
    public Connection connection = null;
    public Statement statement = null;

    public ConnectionToDB(){
        try{
            String url = "jdbc:mysql://127.0.0.1:3306/OrderManagementSystem?user=root&password=Rocky-2811";
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
