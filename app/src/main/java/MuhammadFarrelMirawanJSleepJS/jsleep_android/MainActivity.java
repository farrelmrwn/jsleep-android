package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Account;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.BaseApiService;
import MuhammadFarrelMirawanJSleepJS.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main Activity Class
 *
 * @author Farrel Muhammad
 *
 * Show the list of room that has been created in a list view.
 * Also contain menu top
 */
public class MainActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    public static Account accLogin;
    public static List<Room> getRoom;
    static List<String> getName = new ArrayList<>();
    ListView listView;
    EditText numberlist;
    Button next, prev, go;
    int currentPage = 1, pageSize = 15;
    public static int clicked;

    /**
     * On Create
     *
     * @author Muhammad Farrel Mirawan
     *
     * Have parameter and also paginate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        go = findViewById(R.id.go);
        numberlist = findViewById(R.id.numberlist);
        listView = findViewById(R.id.Listview);
        listView.setOnItemClickListener(this::onItemClick);
        getAllRoom();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                getAllRoom();
                numberlist.setText(String.valueOf(currentPage));
                Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage <= 1){
                    currentPage = 1;
                    Toast.makeText(mContext, "First page", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(currentPage > 1){
                    currentPage--;
                    getAllRoom();
                    numberlist.setText(String.valueOf(currentPage));
                    Toast.makeText(mContext, "Page " + currentPage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = Integer.parseInt(numberlist.getText().toString());
                getAllRoom();
            }
        });
    }

    /**
     * On Item Click Method
     *
     * @author Muhammad Farrel Mirawan
     *
     * If inside of list view is clicked will be directed to detailroomactivity
     */
    private void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent move = new Intent();
        move.setClass(this, DetailRoomActivity.class);
        move.putExtra("Position", position);
        move.putExtra("id", id);
        DetailRoomActivity.ClickedRoom = getRoom.get(position);
        startActivity(move);
    }


    /**
     * OnCreateOptionsMenu
     *
     * @author Muhammad Farrel Mirawan
     *
     * Have menu top
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutop, menu);
        MenuItem AddBox = menu.findItem(R.id.add_button);
        if(accLogin.renter == null){
            AddBox.setVisible(false);
        }
        else {
            AddBox.setVisible(true);
        }
        return true;
    }

    /**
     * OnOptionsItemSelected
     *
     * @author Muhammad Farrel Mirawan
     *
     * Intent for menu if clicked
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.user_button){
            Intent move = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(move);
            return true;
        }
        else if(item.getItemId() == R.id.add_button){
            Intent move = new Intent(MainActivity.this, CreateRoomActivity.class);
            startActivity(move);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * getAllRoom Method
     * Used to get all of the room from the array
     * @author Muhammad Farrel Mirawan
     * @return list of room
     * @see Room
     */
    protected void getAllRoom(){
        mApiService.getAllRoom(currentPage - 1, pageSize).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()){
                    getRoom = (ArrayList<Room>) response.body();
                    ArrayList<String> getroom = new ArrayList<>();
                    assert getRoom != null;
                    for (Room room : getRoom){
                        getroom.add(room.name);
                    }
                    getName.addAll(getroom);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, getroom);
                    listView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}