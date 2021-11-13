package courseTrak.Admin;

import java.util.Random;

/**
 *
 * @author markduah
 */
public class Auth {

    public Auth() {}

    public int verificationCode(){
        String vCode = "";
        Random randNum = new Random();
        int code = randNum.nextInt(999999) + 100000;
        vCode = String.format("%06d", code);
        return Integer.parseInt(vCode);
    }
}
