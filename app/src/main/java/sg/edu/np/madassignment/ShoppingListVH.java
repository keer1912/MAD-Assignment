package sg.edu.np.madassignment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListVH extends RecyclerView.ViewHolder{
    TextView txtTitle;
    TextView txtDesc;
    ImageView img;
    Button btn;
    public ShoppingListVH(View listView) {
        super(listView);
        txtTitle = listView.findViewById(R.id.txtListTitle);
        txtDesc = listView.findViewById(R.id.txtListIngd);
        img = listView.findViewById(R.id.img_shopping);
        btn = listView.findViewById(R.id.listButton);
    }
}
