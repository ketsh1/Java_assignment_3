import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class User_menu {



    public static String menu(){
        System.out.println("OPTIONS:");
        System.out.println("1 - for registration");
        System.out.println("2 - for login");
        System.out.println("exit - for finish the program");
        System.out.print("Type here: ");
        Scanner sc = new Scanner(System.in);
        String user_choose = sc.nextLine();
        return user_choose;
    }


    public static void main_menu(){
        Scanner sc = new Scanner(System.in);
        DB_functions db = new DB_functions();
        Connection conn = db.connect_to_db("Assignment_3_db","postgres","admin");
        String choose_option = menu();

        while(true) {
            if (choose_option.equals("1")) {
                Login.registration();
                choose_option=menu();
            } else if (choose_option.equals("2")) {
                System.out.print("Input your iin to continue: ");
                String user_iin = sc.nextLine();


                while (Input_checker.check_iin_input(user_iin) != true) {
                    System.out.print("Enter IIN: ");
                    user_iin = sc.nextLine();
                }
                Login user = new Login(conn, user_iin);
                user.login();
                choose_option=menu();
            } else if (choose_option.equals("exit")) {
                System.out.println("Good bye!");
                break;
            }
            else {
                choose_option = menu();
            }
        }
    }
}
