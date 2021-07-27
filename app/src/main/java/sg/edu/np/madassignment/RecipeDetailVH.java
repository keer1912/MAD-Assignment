package sg.edu.np.madassignment;


import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeDetailVH extends RecyclerView.ViewHolder{
    TextView ingredient;
    TextView step;
    ImageButton addBtn;
    ImageButton FavButton;
    DatabaseReference favouriteref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public RecipeDetailVH(View itemview) {
        super(itemview);
        ingredient = itemview.findViewById(R.id.ingredientItem);
        step = itemview.findViewById(R.id.step);
        addBtn = itemview.findViewById(R.id.addToList);
    }
}
