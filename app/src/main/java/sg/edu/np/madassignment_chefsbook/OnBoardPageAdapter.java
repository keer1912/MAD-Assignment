package sg.edu.np.madassignment_chefsbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OnBoardPageAdapter extends RecyclerView.Adapter<OnBoardPageVH>{

    ArrayList<OnBoardPageItems> viewPagerItemArrayList;

    public OnBoardPageAdapter(ArrayList<OnBoardPageItems> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public OnBoardPageVH  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewpager,parent,false);
        return new OnBoardPageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardPageVH holder, int position) {
        OnBoardPageItems viewPagerItem = viewPagerItemArrayList.get(position);

        holder.imageView.setImageResource(viewPagerItem.imageID);
        holder.heading.setText(viewPagerItem.heading);
        holder.desc.setText(viewPagerItem.description);
    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }
}
