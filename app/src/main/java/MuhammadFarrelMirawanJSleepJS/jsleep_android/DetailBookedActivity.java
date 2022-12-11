package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Payment;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * DetailBookedActivity Class
 *
 * @author Muhammad Farrel Mirawan
 *
 * Class that showed the booked detail of the room
 */
public class DetailBookedActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    TextView NameBooked, SizeBooked, BookedAddress, FromBooked, ToBooked, PriceBooked;
    Button RejectButton, AcceptButton, CancelBooked;
    public static Payment temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booked);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        NameBooked = findViewById(R.id.NameBooked);
        SizeBooked = findViewById(R.id.SizeBooked);
        BookedAddress = findViewById(R.id.BookedAddress);
        FromBooked = findViewById(R.id.FromBooked);
        ToBooked = findViewById(R.id.ToBooked);
        PriceBooked = findViewById(R.id.PriceBooked);

        RejectButton = findViewById(R.id.RejectButton);
        AcceptButton = findViewById(R.id.AcceptButton);
        CancelBooked = findViewById(R.id.CancelBooked);

        NameBooked.setText(DetailRoomActivity.ClickedRoom.name);
        SizeBooked.setText(String.valueOf(DetailRoomActivity.ClickedRoom.size));
        BookedAddress.setText(DetailRoomActivity.ClickedRoom.address);
        FromBooked.setText(CreatePaymentActivity.fromDate);
        ToBooked.setText(CreatePaymentActivity.toDate);
        PriceBooked.setText(String.valueOf(DetailRoomActivity.ClickedRoom.price.price));
        AcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AcceptRequest(temp.id);
                Intent moveacc = new Intent(DetailBookedActivity.this, BuyerListActivity.class);
                startActivity(moveacc);
            }
        });
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RejectRequest(temp.id);
                Intent movereject = new Intent(DetailBookedActivity.this, BuyerListActivity.class);
                startActivity(movereject);
            }
        });
        CancelBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movecancel = new Intent(DetailBookedActivity.this, BuyerListActivity.class);
                startActivity(movecancel);
            }
        });
    }
    /**
     * Accept Request Method
     *
     * @author Muhammad Farrel Mirawan
     *
     * If the booked request is accepted
     */
    protected Boolean AcceptRequest(int id){
        mApiService.accept(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    System.out.println("Accepted");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("Failed");
                t.printStackTrace();
            }
        });
        return null;
    }
    /**
     * Reject Requst Method
     *
     * @author Muhammad Farrel Mirawan
     *
     * If the request is rejected
     */
    protected Boolean RejectRequest(int id){
        mApiService.cancel(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    System.out.println("Rejected");
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("Failed");
                t.printStackTrace();
            }
        });
        return null;
    }
}