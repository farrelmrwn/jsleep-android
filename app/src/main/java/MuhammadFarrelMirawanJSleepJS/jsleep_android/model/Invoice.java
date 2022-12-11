package MuhammadFarrelMirawanJSleepJS.jsleep_android.model;

/**
 * Invoice
 *
 * @author Muhammad Farrel Mirawan
 *
 * Used to set Room Rating and Payment Status
 */
public class Invoice extends Serializable{
    public int buyerId;
    public int renterId;
    public RoomRating rating;
    public PaymentStatus status;
    public enum RoomRating{
        NONE,BAD,NEUTRAL,GOOD
    }
    public enum PaymentStatus{
        FAILED,WAITING,SUCCESS
    }
    public Invoice(int id) {
        super(id);
    }

}
