package sg.edu.np.madassignment_chefsbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeItemAdapter extends RecyclerView.Adapter<RecipeItemVH>{
    ArrayList<Recipe> recipeArrayList;
    Context mcontext;

    public RecipeItemAdapter(Context c, ArrayList<Recipe> input) {
        mcontext = c;
        recipeArrayList = input;
    }

    @NonNull
    @Override
    public RecipeItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recipe_card_item,
                parent,
                false);
        return new RecipeItemVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemVH holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.recipeTime.setText(String.valueOf(recipe.getTime())+ " minutes");
        if(recipe.getImg()!=null){
            if(mcontext!=null) {
                Glide.with(mcontext).load(recipe.getImg()).into(holder.recipeImg);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),RecipeDetail.class);
                intent.putExtra("id", recipe.getRecipeId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
