package sg.edu.np.madassignment;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailVH extends RecyclerView.ViewHolder{
    TextView ingredient;
    TextView step;
    ImageButton addBtn;

    public RecipeDetailVH(View itemview) {
        super(itemview);
        ingredient = itemview.findViewById(R.id.ingredientItem);
        step = itemview.findViewById(R.id.step);
        addBtn = itemview.findViewById(R.id.addToList);
    }
}
