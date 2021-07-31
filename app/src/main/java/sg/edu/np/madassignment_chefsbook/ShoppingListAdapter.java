package sg.edu.np.madassignment_chefsbook;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListVH>{
    ArrayList<ShoppingListItem> shoppingListItemList;
    Context context;
    public ShoppingListAdapter(Context c, ArrayList<ShoppingListItem> input) {
        shoppingListItemList = input;
        this.context = c;
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
        Picasso.with(context).load(sl.img).into(holder.img);
        if (sl.ingredientsList != null){
            if (sl.ingredientsList.contains(null)){
                while (sl.ingredientsList.remove(null));
            }
            holder.txtDesc.setText(sl.getIngredientsListCount() + " ingredients remaining");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIngredients = new Intent(holder.itemView.getContext(), ShoppingListIngredients.class);
                viewIngredients.putExtra("ingredientsList", sl.ingredientsList);
                viewIngredients.putExtra("name",sl.name);
                viewIngredients.putExtra("ID", sl.getRecipeId());
                holder.itemView.getContext().startActivity(viewIngredients);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingListItemList.size();
    }
}
