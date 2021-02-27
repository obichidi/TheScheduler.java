package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectorDb {

    private static final String databaseName = "WJ07JwW";
    private static final String dBUrl = "jdbc:mysql://wgudb.ucertify.com/WJ07JwW";
    private static final String userName = "U07JwW";
    private static  String password = "53689044470";
    private static final String driver  = "com.mysql.cj.jdbc.Driver";



    static Connection conn;

    public ConnectorDb(){}
    public static Connection connectDb()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com/WJ07JwW", "U07JwW", "53689044470");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void disconnectDb() throws ClassNotFoundException,SQLException, Exception{
        conn.close();
        System.out.println("Connection Closed");
    }



}



