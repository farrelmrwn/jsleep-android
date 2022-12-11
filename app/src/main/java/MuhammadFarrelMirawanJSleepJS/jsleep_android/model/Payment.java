package MuhammadFarrelMirawanJSleepJS.jsleep_android.model;

import java.util.Date;

/**
 * Payment
 *
 * @author Muhammad Farrel Mirawan
 *
 * Used as a payment variable
 */
public class Payment extends Invoice {
    public Date to;
    public Date from;
    public int roomId;

    public Payment(int id) {
        super(id);
    }
}
