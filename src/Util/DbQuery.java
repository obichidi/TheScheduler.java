package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;


/**
 * this is DbQuery Class
 *
 */
public class DbQuery {


    /**This is the constructor for the DbQuery class*/
    public DbQuery(){}


    private static Statement statement;

    /**
     * this is setStatement method that creates a sql statement
     * @param conn  is the connection
     * @throws SQLException throws a sql exception
     */
    public static void setStatement(Connection conn) throws SQLException {

    statement = conn.createStatement();
    }


    /**
     * this method gets the sql statement
     * @return  returns the statement
     */
    public static Statement getStatement(){
        return statement;
    }
}