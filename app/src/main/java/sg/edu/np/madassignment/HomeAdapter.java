package sg.edu.np.madassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeVH>{
    ArrayList<Recipe> recipeArrayList;
    Context mcontext;

    public HomeAdapter(Context c,ArrayList<Recipe> input) {
        mcontext = c;
        recipeArrayList = input;
    }

    @NonNull
    @Override
    public HomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_home_rv_item,
                parent,
                false);
        return new HomeVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVH holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.recipeTime.setText(String.valueOf(recipe.getTime())+ " minutes");
        //Picasso.with(mcontext).load(recipe.getImg()).into(holder.recipeImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),RecipeDetail.class);
                intent.putExtra("name",recipe.getName());
                intent.putExtra("category",recipe.getCategory());
                intent.putExtra("time",recipe.getTime());
                intent.putExtra("difficulty",recipe.getDifficulty());
                intent.putExtra("likes",recipe.getLikes());
                intent.putExtra("owner",recipe.getOwner());
                intent.putStringArrayListExtra("ingredients",recipe.getIngredients());
                intent.putStringArrayListExtra("steps",recipe.getSteps());
                intent.putStringArrayListExtra("nutrition",recipe.getNutrition());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
