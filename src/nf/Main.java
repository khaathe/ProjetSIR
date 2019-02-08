package nf;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //test amandine
        System.out.println("jecris du test !");

    }

    public static Connection connectionDB(){
        Connection connection =null;

        try{
            connection=ConnectionDB.getConnection();
            if(connection!= null){
                System.out.println("Connection established");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!= null){
                try {
                    connection.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
