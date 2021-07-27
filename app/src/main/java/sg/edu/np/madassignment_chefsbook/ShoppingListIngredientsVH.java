package sg.edu.np.madassignment_chefsbook;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListIngredientsVH extends RecyclerView.ViewHolder{
    TextView ingredientName;
    ImageView deleteButton;
    public ShoppingListIngredientsVH(View itemView) {
        super(itemView);
        ingredientName = itemView.findViewById(R.id.ingredientName);
        deleteButton = itemView.findViewById(R.id.deleteButton);

    }
}
