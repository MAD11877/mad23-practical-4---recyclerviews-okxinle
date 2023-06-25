package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity {

    final String title = "Main Activity";
    private User user; // Declare user as a member variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the user's name from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Create a new User object and load the name into the respective view
        user = new User();
        user.setName(name);

        // Set the text based on the value of the followed variable
        Button followButton = findViewById(R.id.button);
        updateFollowButtonText(followButton);

        // Set an OnClickListener to toggle the button text and update the followed variable
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFollowStatus();
                updateFollowButtonText(followButton);
                showToastMessage();
            }
        });
    }

    private void toggleFollowStatus() {
        user.setFollowed(!user.isFollowed());
    }

    private void updateFollowButtonText(Button followButton) {
        if (user.isFollowed()) {
            followButton.setText("Unfollow");
        } else {
            followButton.setText("Follow");
        }
    }

    private void showToastMessage() {
        if (user.isFollowed()) {
            Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
        }
    }
}
