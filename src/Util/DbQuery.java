package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DbQuery {
    
private static Statement statement;

    public static void setStatement(Connection conn) throws SQLException {

    statement = conn.createStatement();
    }
    public static Statement getStatement(){
        return statement;
    }
}