package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Payment;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * BuyerListActivity Class
 *
 * @author Muhammad Farrel Mirawan
 *
 * Used to show the buyer that rent a room in a list view
 */

public class BuyerListActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    ListView BuyerList;
    Button NextPage, PrevPage;
    public static Room room = null;
    List<Payment> pay;
    List<Payment> buyer;
    int index;
    public static ArrayList<Payment> ListBook;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_list);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        NextPage = findViewById(R.id.NextPage);
        PrevPage = findViewById(R.id.PrevPage);
        BuyerList = findViewById(R.id.BuyerList);
        BuyerList.setOnItemClickListener(this::onItemClick);
        buyer = getRenterPayment(MainActivity.accLogin.id, 0, 10);
        NextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                buyer = getRenterPayment(MainActivity.accLogin.id, currentPage-1, 10);
                Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
            }
        });
        PrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage <= 1) {
                    currentPage = 1;
                    Toast.makeText(mContext, "First page", Toast.LENGTH_SHORT).show();
                    return;
                } else if (currentPage > 1) {
                    currentPage--;
                    buyer = getRenterPayment(MainActivity.accLogin.id, currentPage-1, 10);
                    Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * OnItemClick Method
     *
     * @author Muhammad Farrel Mirawan
     *
     * If item in list view is clicked will be moved to detailbookedactivity class
     */
    private void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent move1 = new Intent();
        index = position;
        move1.setClass(mContext, DetailBookedActivity.class);
        move1.putExtra("Position", position);
        move1.putExtra("id", id);
        startActivity(move1);
    }
    /**
     * getRenterPayment Method
     *
     * @author Muhammad Farrel Mirawan
     * @see Payment
     *
     * Used to get the id and name to put in list view
     */
    protected List<Payment> getRenterPayment(int renterId, int page, int pageSize){
        mApiService.getRenterPayment(renterId,0,10).enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                if (response.isSuccessful()){
                    ArrayList<String> payment = new ArrayList<>();
                    pay = response.body();
                    assert pay != null;
                    for (int i = 0; i < pay.size(); i++){
                        payment.add(MainActivity.getName.get(pay.get(i).roomId));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, payment);
                    BuyerList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
        return null;
    }
}