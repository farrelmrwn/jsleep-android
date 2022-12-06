package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Account;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    public static Account accLogin;
    static ArrayList<Room> listRoom = new ArrayList<Room>();
    int current;
    Button next;
    Button prev;
    ListView viewlist;
    List<Room> acc;
    List<Room> temp;
    List<Room> disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_Jsleepandroid);
        ArrayList<Room> ListRoom = new ArrayList<>();
        ArrayList<String> listId = new ArrayList<>();
        current = 0;
        mContext = this;
        mApiService = UtilsApi.getApiService();
        viewlist = findViewById(R.id.Listview);
        viewlist.setOnClickListener(this::onClickItem);
        acc = getRoomList(0, 10);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current <= 1){
                    current = 1;
                    Toast.makeText(mContext, "First page", Toast.LENGTH_SHORT).show();
                    return;
                }
                current--;
                disp = getRoomList(current, 3);
                ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(mContext, android.R.layout.simple_list_item_1, disp);
                viewlist.setAdapter(adapter);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp.size() > current){
                    current = 1;
                    return;
                }
                current++;
                acc = getRoomList(current - 1, 1);
                Toast.makeText(mContext, "Page " + current, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.user_button){
            Intent move = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(move);
            return true;
        }
        else if(item.getItemId() == R.id.add_button){
            Intent move = new Intent(MainActivity.this, CreateRoom.class);
            startActivity(move);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected List<Room> getRoomList(int page, int pageSize){
        mApiService.getAllRoom(page, pageSize).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()){
                    ArrayList<String> temp = new ArrayList<>();
                    disp = response.body();
                    for (Room i : disp){
                        temp.add(i.name);
                    }
                    System.out.println(disp);
                    ArrayAdapter<String> Adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, temp);
                    ListView listview = findViewById(R.id.Listview);
                    listview.setAdapter(Adapter);
                    System.out.println("Success");
                    Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("Failed");
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}