import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Input_checker {

    public static String hashing_password(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }


    public static boolean check_password_strength(String password) {
        int password_length =8, up_chars=0, low_chars=0;
        int special=0, digits=0;
        char ch;
        int total = password.length();
        if(total< password_length) {
            System.out.println("\nThe Password is invalid!");
            return false;
        } else {
            for(int i=0; i<total; i++) {
                ch = password.charAt(i);
                if(Character.isUpperCase(ch))
                    up_chars = 1;
                else if(Character.isLowerCase(ch))
                    low_chars = 1;
                else if(Character.isDigit(ch))
                    digits = 1;
                else
                    special = 1;
            }
        }
        if(up_chars==1 && low_chars==1 && digits==1 && special==1){
            System.out.println("\nThe Password is Strong.");
            return true;
        } else{
            System.out.println("\nThe Password is Weak.");
            return false;
        }
    }


    public static boolean check_iin_input(String iin) {
        int iin_length = 12;
        int total = iin.length();
        if(total==iin_length){
            System.out.println("IIN is correct!");
            return true;
        }
        else{
            System.out.println("IIN should 12 characters long");
            return false;
        }
    }
}
