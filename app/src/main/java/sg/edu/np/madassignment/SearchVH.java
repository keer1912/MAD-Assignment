package sg.edu.np.madassignment;

import android.view.View;

import androidx.annotation.NonNull;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


public class SearchVH extends RecyclerView.ViewHolder {

    public TextView Name;
    public TextView Description;
    public ImageView Img;

    public SearchVH(@NonNull View v) {
        super(v);
        Name = v.findViewById(R.id.tvSearchName);
        Description = v.findViewById(R.id.tvSearchDescription);
        Img = v.findViewById(R.id.SearchImg);
    }
}
