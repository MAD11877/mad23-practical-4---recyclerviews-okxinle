package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    int count = 1;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<user> userlist = generateRandomUsers(20);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        adapter mAdapter = new adapter(ListActivity.this, userlist);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private int random() {
        Random ran = new Random();
        return ran.nextInt(999999999);
    }

    private ArrayList<user> generateRandomUsers(int count) {
        ArrayList<user> userList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int ran1 = random();
            int ran2 = random();
            String name = "Name" + ran1;
            String description = "Description " + ran2;
            boolean followed = false;
            int id = this.count++;

            user newUser = new user(name, description, id, followed);
            userList.add(newUser);
        }

        return userList;
    }
}
