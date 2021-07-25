package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeUser;
    TextView welcomeText;
    TextView category;
    TextView userName;
    private FirebaseAuth mAuth;
    private ArrayList<Recipe> list;

    RecyclerView recyclerView;
    RecipeItemAdapter recipeItemAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rv);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            welcomeUser = findViewById(R.id.welcomeUser);
            welcomeText = findViewById(R.id.welcomeText);

            welcomeUser.setText("Welcome " + user.getDisplayName());
            welcomeText.setText("What do you want to cook today?");
        }

        recyclerView = findViewById(R.id.RecyclerView1);
//      recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase =  firebaseDatabase.getReference().child("recipes");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list = new ArrayList<>();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot eachSnapshot: snapshot.getChildren()){
                    if (eachSnapshot.child("name").getValue() == null) break;
                    if (eachSnapshot.child("category").getValue().equals("30 minutes and Under") ){
                        category = findViewById(R.id.category);
                        category.setText("30 minutes and under");

                        Recipe r = eachSnapshot.getValue(Recipe.class);

                        //recipe.setIngredients(eachSnapshot.child("ingredients").getValue());
                        //Log.e("string", r.ingredients.get(1));

                        Log.d("DB", eachSnapshot.child("time").getValue().toString());
                        list.add(r);
                    }
                }
                recipeItemAdapter = new RecipeItemAdapter(mContext,list);
                recyclerView.setAdapter(recipeItemAdapter);
                Log.v("TEST", list.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

}