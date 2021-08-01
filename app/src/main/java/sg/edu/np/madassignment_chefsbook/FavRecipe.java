package sg.edu.np.madassignment_chefsbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class FavRecipe extends AppCompatActivity {
    TextView favHeader;
    ListView favList;
    private FirebaseAuth mAuth;
    ArrayList<Recipe> favRecipeArrayList;
    FavouriteRecipeAdapter adapter;


//    ImageButton backButton = ImageButton findViewById(R.id.backButton);
//    button.setOnClickListener(new View.OnClickListener){
//        @Override
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_recipe);

        favHeader = findViewById(R.id.favHeader);
//        favHeader.setText()   //categoryname

        favRecipeArrayList = new ArrayList<>();
        adapter = new FavouriteRecipeAdapter(this, favRecipeArrayList);
        favList = findViewById(R.id.favList);
        favList.setAdapter(adapter);

        Intent intent = getIntent();
        String category = intent.getStringExtra("name");
        favHeader.setText(category);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null) {
            String UID = user.getUid();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference mDatabase  =  firebaseDatabase.getReference().child("users").child(UID).child("Favourite");

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue() != null) {
                        //Creating a HashMap object
                        ArrayList<HashMap<String,Object>> favListOfTheUser = (ArrayList<HashMap<String,Object>>) snapshot.getValue();

                        for(int i = 0; i < favListOfTheUser.size(); i++){
                            HashMap<String,Object> item = (HashMap<String,Object>) favListOfTheUser.get(i);
                            if(item.get("category").toString().equals(category)){
                                Recipe r = new Recipe(item.get("name").toString(),item.get("category").toString());
                                r.setImg(item.get("img").toString());
                                r.setTime(((Long)item.get("time")).intValue());
                                favRecipeArrayList.add(r);
                            }
                        }


                        adapter.notifyDataSetChanged();
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}


class FavouriteRecipeAdapter extends ArrayAdapter<Recipe> {

    //ArrayList<CategoryFavourite> categoryList;

    public FavouriteRecipeAdapter(Context context, ArrayList<Recipe> recipeList) {
        super(context,0,recipeList);
        //this.categoryList = categoryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Recipe r = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_favrecipe, parent, false);
        }
        // Lookup view for data population

        ImageView foodImage = (ImageView)  convertView.findViewById(R.id.foodImage);
        Picasso.with(getContext()).load(r.getImg()).into(foodImage);
        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        // Populate the data into the template view using the data object
        textViewName.setText(r.getName());
        tvTime.setText(r.getTime() + "Minutes");
        // Return the completed view to render on screen*/
        return convertView;
    }
}