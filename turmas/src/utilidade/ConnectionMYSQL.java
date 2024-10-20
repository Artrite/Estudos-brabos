package utilidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMYSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Barigui1";

    public static Connection connection = null;


    public static Connection openConnection(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem com o "+ e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Problem com o "+ e.getMessage());
        }
    }
}