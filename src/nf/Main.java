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


    public static class ConnectionDB{

        public Connection getConnection(){
            Connection connection =null;

            try{
                Class.forName("ProjetSIR/lib/mysql-connector-java-8.0.14.jar");
                System.out.println("Driver oki");
                connection.DriverManager.getConnection("localhostDB","userName","password");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return connection;
        }
    }
}
