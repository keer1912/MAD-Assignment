package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnBoardPageVH extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView heading, desc;

    public OnBoardPageVH(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.viewpager_img);
        heading = itemView.findViewById(R.id.heading);
        desc = itemView.findViewById(R.id.description);
    }
}