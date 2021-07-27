package sg.edu.np.madassignment_chefsbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchVH>{
    Context context;
    ArrayList<Search> data;

    public SearchAdapter(Context c, ArrayList<Search> l) {
        context = c;
        data = l;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public SearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_search_recipe,
                parent,
                false
        );
        return new SearchVH(item);
    }

    @Override
    public void onBindViewHolder(SearchVH holder, int position) {
        Search search = data.get(position);
        holder.Name.setText(search.getName());
        holder.Description.setText(search.getDescription());
        holder.Img.setImageURI(search.getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),RecipeDetail.class);
                intent.putExtra("id", search.getRecipeId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterList(ArrayList<Search> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }
}
