package sg.edu.np.madassignment;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.Model;

import java.util.ArrayList;
import java.util.List;

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
