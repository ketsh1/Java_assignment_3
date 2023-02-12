import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //CONNECT TO DB
        DB_functions db = new DB_functions();
        Connection conn = db.connect_to_db("Assignment_3_db","postgres","admin");
        Scanner sc = new Scanner(System.in);

//        System.out.println("OPTIONS:");
//        System.out.println("1 - for registration");
//        System.out.println("2 - for login");
//        System.out.println("exit - for finish the program");
//        System.out.print("Type here: ");
//        String user_choose = sc.nextLine();


        User_menu.main_menu();

//        while(true) {
//            if (User.equals("1")) {
//                Login.registration();
//            } else if (user_choose.equals("2")) {
//                System.out.print("Input your iin to continue: ");
//                String user_iin = sc.nextLine();
//                while (Input_checker.check_iin_input(user_iin) != true) {
//                    System.out.print("Enter IIN: ");
//                    user_iin = sc.nextLine();
//                }
//                Login user = new Login(conn, user_iin);
//                user.login();
//            } else if (user_choose.equals("exit")) {
//                System.out.println("Good bye!");
//                break;
//            }
//            else {
//                System.out.println("Choose correct option!");
//                System.out.print("Type here: ");
//                user_choose = sc.nextLine();
//            }
//        }






    }
}