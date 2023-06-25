package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<User> userList;
    private OnItemClickListener listener;
    private Context context;

    public CustomAdapter(ArrayList<User> userList, OnItemClickListener listener, Context context) {
        this.userList = userList;
        this.listener = listener;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onImageClick(String name);
        void onViewClick();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView1;
        public ImageView imageView2;
        public TextView nameTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);
            imageView1 = view.findViewById(R.id.imageView3);
            imageView2 = view.findViewById(R.id.imageView5);
            nameTextView = view.findViewById(R.id.textView4);
            descriptionTextView = view.findViewById(R.id.textView5);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.nameTextView.setText(user.getName());
        holder.descriptionTextView.setText(user.getDescription());

        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(user.getName());
            }
        });
    }

    private void showDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(name)
                .setMessage("Do you want to view the profile?")
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onViewClick();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }
}
