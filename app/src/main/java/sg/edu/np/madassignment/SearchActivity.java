package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rvSearchList;
    private TextInputEditText searchField;
    DatabaseReference ref;
    ArrayList<Search> searchList = new ArrayList<Search>();
    Context mContext;
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = findViewById(R.id.searchField);
        rvSearchList = findViewById(R.id.rvSearchList);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = firebaseDatabase.getReference().child("recipes");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot eachSnapshot : snapshot.getChildren()) {
                    if(eachSnapshot.child("name").getValue() == null) break;

                    Search s = new Search();
                    s.setRecipeId(eachSnapshot.getKey());
                    s.setName(eachSnapshot.child("name").getValue().toString());
                    s.setDescription(eachSnapshot.child("description").getValue().toString());
                    if(eachSnapshot.child("img").getValue() != null) {
                        Uri uri = Uri.parse(eachSnapshot.child("img").getValue().toString());
                        s.setImg(uri);
                    }
                    Log.d("search", s.getRecipeId() + s.getName() + s.getDescription() + s.getImg());
                    searchList.add(s);
                }
                Collections.shuffle(searchList);
                adapter = new SearchAdapter(getApplicationContext(), searchList);
                LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
                rvSearchList.setLayoutManager(lm);
                rvSearchList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Database", "Database Cancelled");
            }
        });
    }

    private void filter(String text) {
        ArrayList<Search> filteredList = new ArrayList<>();

        for (Search search  : searchList) {
            if(search.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(search);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Bottom Nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Search);

        FloatingActionButton fabAddRecipe = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddRecipeActivity.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Search:
                        return true;
                    case R.id.Add:
                        startActivity(new Intent(getApplicationContext(), AddRecipeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.List:
                        startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}