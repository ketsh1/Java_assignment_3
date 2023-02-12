import java.util.Random;

public class Captcha_generator {
    public static String getCaptcha()
    {
        char data[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char index[] = new char[6];
        Random r = new Random();
        for(int i=0; i<index.length;i++)
        {
            int ran=r.nextInt(data.length);
            index[i]=data[ran];
        }
        return new String(index);
    }
}
