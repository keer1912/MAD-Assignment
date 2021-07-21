package sg.edu.np.madassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeDetailStepsAdapter extends RecyclerView.Adapter<RecipeDetailVH>{
    ArrayList<String> stepsList;

    public RecipeDetailStepsAdapter(ArrayList<String> input) {
        this.stepsList = input;
    }

    @NonNull
    @Override
    public RecipeDetailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_recipe_detail_step_item,
                parent,
                false);
        return new RecipeDetailVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailVH holder, int position) {
        String s = stepsList.get(position);
        holder.step.setText(s);
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }
}
