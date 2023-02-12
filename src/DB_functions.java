import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_functions {

    public Connection connect_to_db(String db_name, String user, String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db_name,user,pass);
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }


    public void create_table(Connection conn, String table_name){
        Statement statement;
        try{
            String query="create table "+table_name+"(iin varchar(12), username varchar(40),password varchar(40), primary key(iin));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void insert_row(Connection conn,String table_name,String iin,String username, String password){
        Statement statement;
        try {
            String query = String.format("insert into %s(iin,username,password) values('%s','%s','%s');",table_name,iin,username,password);
            statement=conn.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet rs = null;
        try {
            String query=String.format("select * from %s",table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("iin")+" ");
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }


}
