package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView searchResultList;
    RecipeItemAdapter recipeItemAdapter;
    private ImageButton searchBtn;
    private EditText searchField;
    DatabaseReference ref;
    ArrayList<Recipe> list;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = findViewById(R.id.searchField);
        searchResultList = findViewById(R.id.searchResultRV);
        searchBtn = findViewById(R.id.searchBtn);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = firebaseDatabase.getReference().child("recipes");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = searchField.getText().toString();
                firebaseRecipeSearch(searchText);
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        searchResultList.setLayoutManager(llm);
    }

    private void firebaseRecipeSearch(String searchText) {
        list = new ArrayList<>();
        Toast.makeText(SearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = ref.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        firebaseSearchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot eachSnapshot: snapshot.getChildren()){
                    Recipe r = eachSnapshot.getValue(Recipe.class);

                    //recipe.setIngredients(eachSnapshot.child("ingredients").getValue());
                    //Log.e("string", r.ingredients.get(1));

                    Log.d("DB", snapshot.child("time").getValue().toString());
                    list.add(r);
                }
                recipeItemAdapter = new RecipeItemAdapter(mContext,list);
                searchResultList.setAdapter(recipeItemAdapter);
                Log.v("TEST", list.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}