package dbControler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
    private static DBController dbController;
    private  Connection connection;

    DBController(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/iqr","root","1234");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Connection getConnection(){
        return  connection;
    }

    public static  DBController getInstance(){
        if(dbController==null){
            dbController=new DBController();
        }
        return dbController;
    }
}
