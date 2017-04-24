package testing;

import core.Email;

import javax.mail.MessagingException;

public class EmailTest {

    public static void main(String [] args) {

        Email email = new Email();
        try {
            email.emailReceipt("krizzle2home@gmail.com", "Inutra.41", "david94k@gmail.com", "Letterkenny Electrical", "HI DAVID!");
        } catch(MessagingException mse) {
            mse.printStackTrace();
        }
    }
}
