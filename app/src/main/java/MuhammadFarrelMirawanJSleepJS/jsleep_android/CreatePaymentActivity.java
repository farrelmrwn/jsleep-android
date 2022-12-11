package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;

public class CreatePaymentActivity extends AppCompatActivity {
    public static String toDate;
    public static String fromDate;
    Button ButtonContinue, CancelPayment;
    EditText Payment_From, Payment_to;
    DatePickerDialog dateFrom, dateTo;
    TextView HeadHotelName;
    Room HeadRoom = MainActivity.getRoom.get(MainActivity.clicked);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment);

        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        Payment_From = findViewById(R.id.Payment_From);
        Payment_to = findViewById(R.id.Payment_to);
        ButtonContinue = findViewById(R.id.ButtonContinue);
        CancelPayment = findViewById(R.id.CancelPayment);
        HeadHotelName = findViewById(R.id.HeaderHotelName);
        HeadHotelName.setText(HeadRoom.name);

        Payment_From.setOnClickListener(v ->{
            dateFrom.show();
        });

        Payment_to.setOnClickListener(v ->{
           dateTo.show();
        });

        dateFrom = new DatePickerDialog(CreatePaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Payment_From.setText(year + "-" + (month + 1) + "-" + day);
                fromDate = year + "-" + (month + 1) + "-" + day;
            }
        }, year, month, day);

        dateTo = new DatePickerDialog(CreatePaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Payment_to.setText(year + "-" + (month + 1) + "-" + day);
                toDate = year + "-" + (month + 1) + "-" + day;
            }
        }, year, month, day);
        ButtonContinue.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view) {
                fromDate = Payment_From.getText().toString();
                toDate = Payment_to.getText().toString();
                Intent move = new Intent(CreatePaymentActivity.this,PaymentInvoiceActivity.class);
                startActivity(move);
            }
        });
    }
}