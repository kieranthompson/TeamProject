package testing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class dateTester {
    static Date date = new Date();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static String currentDate = sdf.format(date);
    static boolean check = false;

    public static void main(String [] args) {

        checkTime();
    }

    public static void checkTime() {
        while(!check) try {
            date = new Date();
            Thread.sleep(5000);
            if (date.getMinutes() < 50) {
                System.out.println("nope");
            } else {
                System.out.println("yup");
                check = true;
            }
            checkTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
