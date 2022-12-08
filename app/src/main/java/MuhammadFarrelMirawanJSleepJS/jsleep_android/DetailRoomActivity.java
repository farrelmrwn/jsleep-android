package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;

public class DetailRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    Room ClickedRoom = MainActivity.getRoom.get(MainActivity.clicked);
    TextView ShowName;
    TextView ShowBedType;
    TextView ShowSize;
    TextView ShowPrice;
    TextView ShowAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        ShowName = findViewById(R.id.ShowName);
        ShowBedType = findViewById(R.id.ShowBedType);
        ShowSize = findViewById(R.id.ShowSize);
        ShowPrice = findViewById(R.id.ShowPrice);
        ShowAddress = findViewById(R.id.ShowAddress);
        ShowName.setText(ClickedRoom.name);
        ShowBedType.setText(ClickedRoom.bedType.toString());
        ShowSize.setText(String.valueOf(ClickedRoom.size));
        ShowPrice.setText(String.valueOf(ClickedRoom.price.price));
        ShowAddress.setText(ClickedRoom.address);
    }
}