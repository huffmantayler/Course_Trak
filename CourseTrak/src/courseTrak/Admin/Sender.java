package courseTrak.Admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author markduah
 */

public class Sender {
    private static final String ACC_SID = "AC5776508433ac5112d3501c0da45956f7";
    private static final String TOKEN = "c77ae61227d371505d5a7fee7669b417";
    private static final String MY_NUMBER = "+12058786573";

    public void sendMessage(String phoneNumber, String Code)throws IOException {
        String currentTime = (new SimpleDateFormat("hh:mm aa")).format(new Date());
        Twilio.init(ACC_SID, TOKEN);
        Message message = (Message)Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(MY_NUMBER),
                Code).create();
        System.out.println("Message Successfully Sent to " + phoneNumber + " at [" + currentTime + "]");
    }
}
