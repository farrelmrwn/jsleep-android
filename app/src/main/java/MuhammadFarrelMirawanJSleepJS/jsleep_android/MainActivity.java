package MuhammadFarrelMirawanJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.Gson;

import MuhammadFarrelMirawanJSleepJS.jsleep_android.model.Room;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream filepath = null;
        ArrayList<Room> room = new ArrayList<Room>();
        ArrayList<String> name = new ArrayList<String>();
        Gson gson = new Gson();

        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader read = new BufferedReader(new InputStreamReader(filepath));
            Room[] Rooms = gson.fromJson(read, Room[].class);
            Collections.addAll(room, Rooms);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Room r : room) {
            name.add(r.name);
        }

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        ListView list = findViewById(R.id.Listview);
        list.setAdapter(roomAdapter);
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
        return super.onOptionsItemSelected(item);
    }
}