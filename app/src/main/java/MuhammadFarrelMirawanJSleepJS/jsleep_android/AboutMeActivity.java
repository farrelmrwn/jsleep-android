package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView name, email, balance;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        name = findViewById(R.id.showname);
        email = findViewById(R.id.showemail);
        balance = findViewById(R.id.showbalance);
        name.setText(LoginActivity.accLogin.name);
        email.setText(LoginActivity.accLogin.email);
        balance.setText(String.valueOf(LoginActivity.accLogin.balance));
    }
}