package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Renter;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutMeActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView name, email, balance;
        EditText showamount;
        Button TopUp;
        ImageView logo;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        name = findViewById(R.id.showname);
        email = findViewById(R.id.showemail);
        balance = findViewById(R.id.showbalance);
        logo = findViewById(R.id.logoJsleep);
        name.setText(MainActivity.accLogin.name);
        email.setText(MainActivity.accLogin.email);
        balance.setText(String.valueOf(MainActivity.accLogin.balance));

        CardView CardShow = findViewById(R.id.CardShow);
        TextView ShowNameRenter = findViewById(R.id.ShowNameRenter);
        TextView ShowAddressRenter = findViewById(R.id.ShowAddressRenter);
        TextView ShowNumber = findViewById(R.id.ShowNumber);

        CardView CardRenterButton = findViewById(R.id.CardRenterButton);
        Button RegisterRenter = findViewById(R.id.RegisterRenter);

        CardView CardFill = findViewById(R.id.CardFill);
        EditText RenterName = findViewById(R.id.RenterName);
        EditText RenterAddress = findViewById(R.id.RenterAddress);
        EditText RenterNumber = findViewById(R.id.RenterNumber);
        Button ButtonRegist = findViewById(R.id.ButtonRegist);
        Button ButtonCancel = findViewById(R.id.ButtonCancel);

        showamount = findViewById(R.id.showamount);
        TopUp = findViewById(R.id.TopUp);

        TopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopUpAcc(MainActivity.accLogin.id, Double.parseDouble(showamount.getText().toString()));
            }
        });

        if (MainActivity.accLogin.renter != null){
            CardShow.setVisibility(CardView.VISIBLE);
            CardRenterButton.setVisibility(CardView.GONE);
            CardFill.setVisibility(CardView.GONE);
            logo.setVisibility(ImageView.VISIBLE);

            ShowNameRenter.setText(MainActivity.accLogin.renter.username);
            ShowAddressRenter.setText(MainActivity.accLogin.renter.address);
            ShowNumber.setText(MainActivity.accLogin.renter.phoneNumber);
        }
        else{
            CardShow.setVisibility(CardView.GONE);
            CardRenterButton.setVisibility(CardView.VISIBLE);
            CardFill.setVisibility(CardView.GONE);
            logo.setVisibility(ImageView.VISIBLE);
            RegisterRenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CardShow.setVisibility(CardView.GONE);
                    CardRenterButton.setVisibility(CardView.GONE);
                    CardFill.setVisibility(CardView.VISIBLE);
                    logo.setVisibility(ImageView.GONE);
                    ButtonRegist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Renter renter = requestRenter(MainActivity.accLogin.id, RenterName.getText().toString(),
                                    RenterAddress.getText().toString(), RenterNumber.getText().toString());
                            Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                            startActivity(move);
                            recreate();
                        }
                    });
                    ButtonCancel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            CardShow.setVisibility(CardView.GONE);
                            CardRenterButton.setVisibility(CardView.VISIBLE);
                            CardFill.setVisibility(CardView.GONE);
                            logo.setVisibility(ImageView.VISIBLE);
                        }
                    });
                }
            });
        }
    }
    protected Renter requestRenter(int id, String username, String address, String phoneNumber){
        mApiService.registerRenter(id, username, address, phoneNumber).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()){
                    Renter renter;
                    renter = response.body();
                    MainActivity.accLogin.renter = renter;
                    System.out.println(renter.toString());
                    Toast.makeText(mContext, "Renter Successful", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "Renter Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    protected Renter TopUpAcc(int id, double balance){
        mApiService.topUp(id, balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    MainActivity.accLogin.balance = MainActivity.accLogin.balance + balance;
                    System.out.println("Top Up Berhasil");
                    Toast.makeText(mContext, "Top Up Successful", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Top Up Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
