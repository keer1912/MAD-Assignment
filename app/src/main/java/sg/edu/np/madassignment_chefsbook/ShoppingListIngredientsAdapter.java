package sg.edu.np.madassignment_chefsbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShoppingListIngredientsAdapter extends RecyclerView.Adapter<ShoppingListIngredientsVH>{
    ArrayList<String> ingredientsList;
    Context context;
    static ShoppingListIngredientsAdapter sLIA;
    private FirebaseAuth mAuth;
    static String recipeName;

    public ShoppingListIngredientsAdapter(Context c, ArrayList<String> input) {
        this.ingredientsList = input;
        this.context = c;
    }

    @NonNull
    @Override
    public ShoppingListIngredientsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_shoppinglist_ingredient_rv_item,
                parent,
                false);
        return new ShoppingListIngredientsVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListIngredientsVH holder, int position) {
        String s = ingredientsList.get(position);
        holder.ingredientName.setText(s);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(holder.deleteButton.getContext())
                        .setTitle("Remove item from Shopping List?")
                        .setMessage(s)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String num = String.valueOf(position);
                                Log.v("Tester", num);
                                mAuth = FirebaseAuth.getInstance();
                                FirebaseUser user = mAuth.getCurrentUser();
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                DatabaseReference ref =  firebaseDatabase.getReference("users").child(user.getUid()).child("shoppinglist").child(recipeName).child("ingredientsList").child(num);
                                int pos = ingredientsList.indexOf(s);
                                ingredientsList.remove(s);
                                ref.removeValue();

                                Log.v("Ingredients",ingredientsList.toString());
                                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT);
                                sLIA.notifyItemRemoved(pos);
                            }
                        })
                        .setNegativeButton("Close",null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
