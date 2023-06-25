package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    final String title = "Main Activity 2";

    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Randomly generate 20 User objects
        userList = generateUserList();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check the last digit of the name and use a different layout if it contains 7
        CustomAdapter adapter = new CustomAdapter(userList, this,this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<User> generateUserList() {
        ArrayList<User> userList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int randomNumber = random.nextInt(100);
            String name = "User " + randomNumber;
            String description = "Description " + randomNumber;
            boolean followed = random.nextBoolean();

            User user = new User();
            user.setName(name);
            user.setDescription(description);
            user.setFollowed(followed);
            userList.add(user);
        }

        return userList;
    }

    @Override
    public void onImageClick(String name) {
        showAlertDialog(name);
    }

    @Override
    public void onViewClick() {
        // Not used in this example
    }

    private void showAlertDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile")
                .setMessage(name)
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startProfilePage(name);
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Log.v("AlertDialog", "User Declines!");
                    }
                })
                .show();
    }

    private void startProfilePage(String name) {
        Intent intent = new Intent(ListActivity.this, ProfilePage.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
