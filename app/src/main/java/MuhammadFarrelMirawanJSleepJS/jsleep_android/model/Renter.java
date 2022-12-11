package MuhammadFarrelMirawanJSleepJS.jsleep_android.model;
/**
 * Renter
 *
 * @author Muhammad Farrel Mirawan
 *
 * Variable of Renter that will be store renter information
 */
public class Renter extends Serializable{
    public String username;
    public String address;
    public String phoneNumber;

    public Renter(int id) {
        super(id);
    }
}
