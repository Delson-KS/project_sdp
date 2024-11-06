package management;
import java.sql.*;

public class DbFunctions implements db {
    Connection connection = ConnectionToDb.connection_to_server();

    @Override
    public void insert_row_to_dashboard(String table_name,String day,String name_of_subject,String teacher, String classroom){
        Statement statement;
        int id=1;
        if(day.equals("Monday")){
            id=1;
        }
        if(day.equals("Tuesday")) {
            id = 2;
        }
        if (day.equals("Wednesday")){
            id=3;
        }
        if(day.equals("Thursday")){
            id=4;
        }
        if(day.equals("Friday")){
            id=5;
        }
        try{
            String dataOfRow = String.format("insert into %s(id,day_of_week,name_of_subject,teacher,classroom) values('%s','%s','%s','%s','%s')",table_name,id,day,name_of_subject,teacher,classroom);
            statement=connection.createStatement();
            statement.executeUpdate(dataOfRow);
            System.out.println("Row inserted");
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
    @Override
    public void insert_assignments(String table_name,String name_of_subject,String deeadline){
        Statement statement;
        try{
            String dataOfRow = String.format("insert into %s(name_of_subject,deadline) values('%s','%s')",table_name,name_of_subject,deeadline);
            statement=connection.createStatement();
            statement.executeUpdate(dataOfRow);
            System.out.println("Row inserted");
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
    @Override
    public void print_table_dashboard(String table_name){
        Statement statement;
        ResultSet result = null;
        try{
            String data_from_table = String.format("select * from %s order by id",table_name);
            statement =connection.createStatement();
            result=statement.executeQuery(data_from_table);
            while (result.next()){
                System.out.print(result.getString("day_of_week")+" ");
                System.out.print(result.getString("name_of_subject")+" ");
                System.out.print(result.getString("teacher")+" ");
                System.out.println(result.getString("classroom")+" ");

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void print_table_assignments(String table_name){
        Statement statement;
        ResultSet result = null;
        try{
            String data_from_table = String.format("select * from %s order by id",table_name);
            statement =connection.createStatement();
            result=statement.executeQuery(data_from_table);
            while (result.next()){
                //time,name,teacher,assignment
                System.out.print(result.getString("id")+" ");
                System.out.print(result.getString("name_of_subject")+" ");

                System.out.println(result.getString("deadline")+" ");

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void delete_row_by_name(String table_name,String name){
        Statement statement;
        try{
            String delete_command = String.format("delete from %s where name_of_subject='%s'",table_name,name);
            statement=connection.createStatement();
            statement.executeUpdate(delete_command);
            System.out.println("Data deleted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_row_by_id(String table_name,int id){
        Statement statement;
        try{
            String delete_command = String.format("delete from %s where id='%s'",table_name,id);
            statement=connection.createStatement();
            statement.executeUpdate(delete_command);
            System.out.println("Data deleted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}