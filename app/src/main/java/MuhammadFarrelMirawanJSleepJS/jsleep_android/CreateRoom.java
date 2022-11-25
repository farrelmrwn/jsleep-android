package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.BedType;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.City;

public class CreateRoom extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        Spinner SpinnerCity = (Spinner) findViewById(R.id.SpinnerCity);
        SpinnerCity.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));
        Spinner SpinnerBed = (Spinner) findViewById(R.id.SpinnerBed);
        SpinnerBed.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
    }
}