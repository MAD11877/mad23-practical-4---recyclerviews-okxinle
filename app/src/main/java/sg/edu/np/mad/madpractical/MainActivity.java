package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button follow = findViewById(R.id.follow);
        Button message = findViewById(R.id.message);
        TextView name = findViewById(R.id.textView2);
        TextView des = findViewById(R.id.textView);

        user user1 = (user) getIntent().getSerializableExtra("key");
        name.setText(user1.name);
        des.setText(user1.description);
        follow.setText(user1.followed ? "unfollow" : "follow");

        follow.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                if (!user1.followed){
                    follow.setText("unfollow");
                    user1.followed = true;
                    Toast.makeText(getApplicationContext(),
                                    "followed",
                                    Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    follow.setText("follow");
                    user1.followed = false;
                }
            }

        });
    }
}