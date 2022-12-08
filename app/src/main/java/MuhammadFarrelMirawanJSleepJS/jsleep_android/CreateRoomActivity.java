package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.BedType;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.City;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Facility;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    ArrayList<Facility> facility = new ArrayList<Facility>();
    BedType bedType;
    City citySelect;
    EditText NameHotel;
    EditText PriceHotel;
    EditText AddressHotel;
    EditText SizeHotel;
    Button create, cancel;
    CheckBox AC, WiFi, Bathtub, Balcony, Fitness, Pool, Restaurant, Refrigerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        NameHotel = findViewById(R.id.NameHotel);
        PriceHotel = findViewById(R.id.PriceHotel);
        AddressHotel = findViewById(R.id.AddressHotel);
        SizeHotel = findViewById(R.id.SizeHotel);
        AC = findViewById(R.id.ACCheck);
        WiFi = findViewById(R.id.WiFiCheck);
        Bathtub = findViewById(R.id.BathTubCheck);
        Balcony = findViewById(R.id.BalconyCheck);
        Fitness = findViewById(R.id.FitnessCheck);
        Pool = findViewById(R.id.SwimmingCheck);
        Restaurant = findViewById(R.id.RestaurantCheck);
        Refrigerator = findViewById(R.id.RefrigeratorCheck);
        create = findViewById(R.id.ButtonCreate);
        cancel = findViewById(R.id.CancelButton);
        Spinner SpinnerCity = (Spinner) findViewById(R.id.SpinnerCity);
        SpinnerCity.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));
        Spinner SpinnerBed = (Spinner) findViewById(R.id.SpinnerBed);
        SpinnerBed.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                startActivity(move);
            }
        });
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(AC.isChecked()){
                    facility.add(Facility.AC);
                }
                if(WiFi.isChecked()){
                    facility.add(Facility.WiFi);
                }
                if(Bathtub.isChecked()){
                    facility.add(Facility.Bathtub);
                }
                if(Balcony.isChecked()){
                    facility.add(Facility.Balcony);
                }
                if(Fitness.isChecked()){
                    facility.add(Facility.FitnessCenter);
                }
                if(Pool.isChecked()){
                    facility.add(Facility.SwimmingPool);
                }
                if(Restaurant.isChecked()){
                    facility.add(Facility.Restaurant);
                }
                if(Refrigerator.isChecked()){
                    facility.add(Facility.Refrigerator);
                }
                String bedSize = SpinnerBed.getSelectedItem().toString();
                String city = SpinnerCity.getSelectedItem().toString();
                bedType = BedType.valueOf(bedSize);
                citySelect = City.valueOf(city);
                Integer price = new Integer(PriceHotel.getText().toString());
                Integer size = new Integer(SizeHotel.getText().toString());
                int pricePrice = price.parseInt(PriceHotel.getText().toString());
                int sizeSize = size.parseInt(SizeHotel.getText().toString());
                Room room = requestRoom(MainActivity.accLogin.id, NameHotel.getText().toString(), sizeSize, pricePrice, facility, citySelect, AddressHotel.getText().toString(), bedType);
            }
        });
    }
    protected Room requestRoom(int accountId, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType){
        mApiService.createRoom(accountId, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if(response.isSuccessful()){
                    Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Successful at creating a room", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
