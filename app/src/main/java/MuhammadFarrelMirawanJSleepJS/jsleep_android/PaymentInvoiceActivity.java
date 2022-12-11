package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Payment;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * PaymentInvoiceActivity Class
 *
 * @author Muhammad Farrel Mirawan
 *
 * Show the invoice detail of the payment of the room
 */
public class PaymentInvoiceActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    TextView InvoiceName, InvoiceAddress, ShowFrom, ShowTo, ShowTotal, ShowBalanceInvoice;
    Button ContinueButton, BackButton;
    Payment pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_invoice);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        InvoiceName = findViewById(R.id.InvoiceName);
        InvoiceName.setText(DetailRoomActivity.ClickedRoom.name);
        InvoiceAddress = findViewById(R.id.InvoiceAddress);
        InvoiceAddress.setText(DetailRoomActivity.ClickedRoom.address);
        ShowBalanceInvoice = findViewById(R.id.ShowBalanceInvoice);
        ShowBalanceInvoice.setText(String.valueOf(MainActivity.accLogin.balance));

        ShowFrom = findViewById(R.id.ShowFrom);
        ShowTo = findViewById(R.id.ShowTo);
        ShowTotal = findViewById(R.id.ShowTotal);


        ContinueButton = findViewById(R.id.ContinueButton);
        BackButton = findViewById(R.id.BackButton);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from = sdf.parse(CreatePaymentActivity.fromDate);
            Date to = sdf.parse(CreatePaymentActivity.toDate);
            long range = to.getTime() - from.getTime();
            long days = range / (24 * 60 * 60 * 1000);
            ShowTotal.setText(String.valueOf(((double)days) * DetailRoomActivity.ClickedRoom.price.price));
            ShowFrom.setText(CreatePaymentActivity.fromDate);
            ShowTo.setText(CreatePaymentActivity.toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPayment(MainActivity.accLogin.id, DetailRoomActivity.ClickedRoom.accountId, DetailRoomActivity.ClickedRoom.id, CreatePaymentActivity.fromDate, CreatePaymentActivity.toDate);
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move1 = new Intent(PaymentInvoiceActivity.this, CreatePaymentActivity.class);
                startActivity(move1);
            }
        });
    }
    protected Payment createPayment(int buyerId, int renterId, int roomId, String from, String to){
        mApiService.createPayment(buyerId, renterId, roomId, from, to).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()){
                    System.out.println("Create Payment Success");
                    pay = response.body();
                    System.out.println(pay);
                    Intent move = new Intent(PaymentInvoiceActivity.this, MainActivity.class);
                    startActivity(move);
                    Toast.makeText(mContext, "Create Payment Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                Toast.makeText(mContext, "Create Payment Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}