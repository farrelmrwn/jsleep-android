package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Account;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RegisterActivity
 *
 * @author Muhammad Farrel Mirawan
 *
 * To Register to account by putting username, password, and email
 */
public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText name,email,password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        Button login = findViewById(R.id.RegisterButton);
        Button back = findViewById(R.id.backButton);
        name = findViewById(R.id.fillname);
        email = findViewById(R.id.fillemail);
        password = findViewById(R.id.fillPassword);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Account account = requestRegister();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(move1);
            }
        });
    }
    protected Account requestRegister(){
        mApiService.register(name.getText().toString(), email.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                    Toast.makeText(mContext, "Register Successfull", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(move);
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Account Already Registered", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}