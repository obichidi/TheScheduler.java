package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this is the class for the connectorDb

 */
public class ConnectorDb {

    private static final String databaseName = "WJ07JwW";
    private static final String dBUrl = "jdbc:mysql://wgudb.ucertify.com/WJ07JwW";
    private static final String userName = "U07JwW";
    private static  String password = "53689044470";
    private static final String driver  = "com.mysql.cj.jdbc.Driver";
/**This is the constructor for the ConnectorDb class*/
        public ConnectorDb(){}

    static Connection conn;

//    public ConnectorDb(){}


    /**
     * this is the connection method that  opens connection to the server and database
     * @return  conn the database information needed to connect
     */
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

    /**
     * this is the connection method that  closes the connection to the server and database
     * @throws SQLException throws an sql exception
     */
    public static void disconnectDb() throws SQLException  {
        conn.close();
        System.out.println("Connection Closed");
    }



}



