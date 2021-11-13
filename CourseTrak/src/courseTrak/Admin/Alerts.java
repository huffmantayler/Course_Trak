package courseTrak.Admin;

import java.io.IOException;

/**
 *
 * @author markduah
 */
public class Alerts {
    private final Sender sender = new Sender();
    private String number;

    public Alerts(String number) {
        this.number = number;
    }

    public void loginCode(int code) throws IOException {
        String message = "[Course-Trak] Verification code: " +  code + ". " +
                "Do not share this code with anyone.";
        sender.sendMessage(this.number, message);
    }

    public void accessGranted(String number) throws IOException {
        String message = "[Course-Trak] You've successfully been logged in. " +
                "If this isn't you, please contact an administrator.";
        sender.sendMessage(number, message);
    }

    public void accessFailed(String number) throws IOException {
        String message = "[Course-Trak] An attempt to log in has failed. " +
                "Please report this issue to an administrator if this wasn't your attempt.";
        sender.sendMessage(number, message);
    }
}
