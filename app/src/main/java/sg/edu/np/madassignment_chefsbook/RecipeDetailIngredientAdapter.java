package sg.edu.np.madassignment_chefsbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class RecipeDetailIngredientAdapter extends RecyclerView.Adapter<RecipeDetailVH>{
    ArrayList<String> ingredientList;
    private FirebaseAuth mAuth;
    static String recipeName;
    static String img;
    static String recipeID;

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
        //Adding ingredient to database on click
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference ref = db.getReference().child("users")
                        .child(user.getUid()).child("shoppinglist").child(recipeID);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        DatabaseReference nameref = ref.child("name");
                        nameref.setValue(recipeName);
                        DatabaseReference imgref = ref.child("img");
                        imgref.setValue(img);
                        long xy = snapshot.child("ingredientsList").getChildrenCount();
                        DatabaseReference tempref = ref.child("ingredientsList").child(String.valueOf(xy));
                        tempref.setValue(s);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(v.getContext(), "Added to Shopping List", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
