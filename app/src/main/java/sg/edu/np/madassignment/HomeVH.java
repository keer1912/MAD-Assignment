package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HomeVH extends RecyclerView.ViewHolder {
    TextView recipeName;
    TextView recipeTime;
    ImageView recipeImg;

    public HomeVH(View itemView) {
        super(itemView);
        recipeName = itemView.findViewById(R.id.recipeName);
        recipeTime = itemView.findViewById(R.id.recipeTime);
        recipeImg = itemView.findViewById(R.id.recipeImage);
    }
}

