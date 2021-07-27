package sg.edu.np.madassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ShoppingListAdapter shoppingListAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);
        RecyclerView rv = findViewById(R.id.shoppingListRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase =  firebaseDatabase.getReference("users").child(user.getUid()).child("shoppinglist");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> shoppingList = snapshot.getChildren();
                ArrayList<ShoppingListItem> shoppingRecipes = new ArrayList<>();

                for (DataSnapshot item : shoppingList){
                    ShoppingListItem sl = item.getValue(ShoppingListItem.class);
                    shoppingRecipes.add(sl);
                    shoppingListAdapter = new ShoppingListAdapter(shoppingRecipes);
                    rv.setAdapter(shoppingListAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Bottom Nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.List);

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
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Add:
                        startActivity(new Intent(getApplicationContext(), AddRecipeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.List:
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
