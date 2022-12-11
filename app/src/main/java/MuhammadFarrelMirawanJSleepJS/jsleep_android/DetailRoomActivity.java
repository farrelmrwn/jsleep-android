package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Facility;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;

/**
 * DetailRoomActivity class
 *
 * @author Muhammad Farrel Mirawan
 *
 * Show the detail of the room
 */

public class DetailRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Button cancel, book;
    Context mContext;
    static Room ClickedRoom = MainActivity.getRoom.get(MainActivity.clicked);
    TextView ShowName;
    TextView ShowBedType;
    TextView ShowSize;
    TextView ShowPrice;
    TextView ShowAddress;
    CheckBox AC, WiFi, Bathtub, Balcony, Fitness, Pool, Restaurant, Refrigerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        ShowName = findViewById(R.id.InvoiceName);
        ShowBedType = findViewById(R.id.InvoiceAddress);
        ShowSize = findViewById(R.id.ShowFrom);
        ShowPrice = findViewById(R.id.ShowTo);
        ShowAddress = findViewById(R.id.ShowTotal);
        ShowName.setText(ClickedRoom.name);
        ShowBedType.setText(ClickedRoom.bedType.toString());
        ShowSize.setText(String.valueOf(ClickedRoom.size));
        ShowPrice.setText(String.valueOf(ClickedRoom.price.price));
        ShowAddress.setText(ClickedRoom.address);

        AC = findViewById(R.id.ACCheck);
        WiFi = findViewById(R.id.WiFiCheck);
        Bathtub = findViewById(R.id.BathTubCheck);
        Balcony = findViewById(R.id.BalconyCheck);
        Fitness = findViewById(R.id.FitnessCheck);
        Pool = findViewById(R.id.SwimmingCheck);
        Restaurant = findViewById(R.id.RestaurantCheck);
        Refrigerator = findViewById(R.id.RefrigeratorCheck);

        cancel = findViewById(R.id.ButtonCancel);
        book = findViewById(R.id.ButtonBook);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(DetailRoomActivity.this, MainActivity.class);
                startActivity(move);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move1 = new Intent(DetailRoomActivity.this, CreatePaymentActivity.class);
                startActivity(move1);
            }
        });

        for (int i = 0; i < ClickedRoom.facility.size(); i++) {
            if (ClickedRoom.facility.get(i).equals(Facility.AC )) {
                AC.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.WiFi)) {
                WiFi.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.Bathtub)) {
                Bathtub.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.Balcony)) {
                Balcony.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.FitnessCenter)) {
                Fitness.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.SwimmingPool)){
                Pool.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.Restaurant)) {
                Restaurant.setChecked(true);
            } else if (ClickedRoom.facility.get(i).equals(Facility.Refrigerator)) {
                Refrigerator.setChecked(true);
            }
        }
    }
}