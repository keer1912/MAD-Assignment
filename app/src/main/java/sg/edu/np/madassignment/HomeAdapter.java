package sg.edu.np.madassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.recipeTime.setText(String.valueOf(recipe.getTime()));
        //holder.recipeImg.s(recipe.getImage());
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
