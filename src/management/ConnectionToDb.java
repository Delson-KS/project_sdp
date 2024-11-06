package management;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDb {
    protected static final String password = "Billeryolo";
    protected static final String userName = "postgres";
    public static Connection connection_to_server(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            //"jdbc:postgresql://localhost:5432/Database of something"
            // tcp://0.tcp.eu.ngrok.io:19584
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Database of something",userName, password);
         /*   if(connection!=null){
                System.out.println("Connection to server Established");
            }
            else{
                System.out.print("Connection failed");
            }*/
        }
        catch(Exception e){
            System.out.println(e);
        }
        return  connection;
    }
}