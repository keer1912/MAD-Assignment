package sg.edu.np.madassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListVH>{
    ArrayList<ShoppingListItem> shoppingListItemList;
    Context context;

    public ShoppingListAdapter(Context c, ArrayList<ShoppingListItem> input) {
        shoppingListItemList = input;
        context = c;
    }

    @NonNull
    @Override
    public ShoppingListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_shoppinglist_rv_item,
                parent,
                false);
        return new ShoppingListVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListVH holder, int position){
        ShoppingListItem sl = shoppingListItemList.get(position);
        holder.txtTitle.setText(sl.name);
        holder.txtDesc.setText((sl.ingredientsList.size()) + " ingredients remaining");

        /**holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Ingredient List view
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return shoppingListItemList.size();
    }
}
