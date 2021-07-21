package sg.edu.np.madassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeDetailIngredientAdapter extends RecyclerView.Adapter<RecipeDetailVH>{
    ArrayList<String> ingredientList;

    public RecipeDetailIngredientAdapter(ArrayList<String> input) {
        this.ingredientList = input;
    }

    @NonNull
    @Override
    public RecipeDetailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_recipe_detail_ingredient_item,
                parent,
                false);
        return new RecipeDetailVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailVH holder, int position) {
        String s = ingredientList.get(position);
        holder.ingredient.setText(s);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
