import java.sql.*;
import java.util.Scanner;

public class Login implements User{
    private String iin;
    private String username;
    private String password;
// ДОБАВИТЬ СЮДА СТРИНГ ЮЗЕРНАЙМ И ОФОРМИТЬ РЕГИСТРАЦИЮ ОТСЮДА
    public Login(Connection conn, String iin){
        Statement statement;
        ResultSet rs = null;
        try{
            String query=String.format("select * from userdata where iin ='%s'",iin);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                this.iin = rs.getString("iin");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static void registration(){
        //CONNECTING TO DB
        DB_functions db = new DB_functions();
        Connection conn = db.connect_to_db("Assignment_3_db","postgres","admin");
        Scanner sc = new Scanner(System.in);
        Statement statement;

        //INPUT IIN AND CHECKS THE LENGTH OF IIN
        System.out.print("Enter IIN: ");
        String iin_input = sc.nextLine();
        while(Input_checker.check_iin_input(iin_input)!=true) {
            System.out.print("Enter IIN: ");
            iin_input = sc.nextLine();
        }
        //INPUT USERNAME
        System.out.print("Enter username: ");
        String username_input = sc.nextLine();
        //INPUT PASSWORD
        System.out.print("Enter password: ");
        String password_input = sc.nextLine();

        //CHECKS PASSWORD STRENGTH
        while(Input_checker.check_password_strength(password_input)!=true) {
            System.out.println("The password must consist of at least 8 characters, must contain at least one capital letter, special symbol and a number.");
            System.out.print("Enter password: ");
            password_input = sc.nextLine();
        }

        //CHECKS IF THERE IS DUPLICATE OF IIN IN DB

        while(true){
            try{
                // insert row into userdata table
                String query = String.format("insert into %s(iin,username,password) values('%s','%s','%s');","userdata",iin_input,username_input, Input_checker.hashing_password(password_input));
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Registered Successfully");
                break;
            }catch (Exception e){
                //System.err.println("Error inserting row into userdata table: " + e.getMessage());
                System.out.println("Try again! This IIN is already exists");
                System.out.print("Enter IIN: ");
                iin_input = sc.nextLine();
            }
        }
    }


    public void login() {
        //CONNECT TO DB
        DB_functions db = new DB_functions();
        Connection conn = db.connect_to_db("Assignment_3_db","postgres","admin");
        Scanner sc = new Scanner(System.in);
        int try_counter = 3;

        System.out.print("Enter login or iin: ");
        String username_input = sc.nextLine();
        Login user1 = new Login(conn, username_input);
        System.out.print("Enter password: ");
        String password_input = sc.nextLine();

        while(try_counter>=0) {
            if (this.iin.equals(username_input) && this.password.equals(Input_checker.hashing_password(password_input))) {
                System.out.println("Logged in successfully!");
                System.out.println("=====================================");
                break;
            }
            else if(this.username.equals(username_input)&&this.password.equals(Input_checker.hashing_password(password_input))){
                System.out.println("Logged in successfully!");
                System.out.println("=====================================");
                break;
            }
            else{
                try_counter--;
                if(try_counter==0){
                    System.out.println("Enter captcha for continue!");
                    String captcha = Captcha_generator.getCaptcha();
                    System.out.print("CAPTCHA: ");
                    System.out.println(captcha);
                    String captcha_input = sc.nextLine();

                    if(captcha.equals(captcha_input)){
                        System.out.println("You have passed captcha");
                        try_counter = 3;
                    }else{
                        System.out.println("Try again later!");
                        break;
                    }
                }
                System.out.println("You have left "+try_counter+" try(-ies)");
                System.out.print("Enter login or iin: ");
                username_input = sc.nextLine();
                System.out.print("Enter password: ");
                password_input = sc.nextLine();
            }
        }

    }
}
