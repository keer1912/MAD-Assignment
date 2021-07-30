package sg.edu.np.madassignment_chefsbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ShoppingListIngredients extends AppCompatActivity {
    ShoppingListIngredientsAdapter shoppingListIngredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist_ingredient);

        ArrayList<String> ingredientsList = getIntent().getExtras().getStringArrayList("ingredientsList");
        if (ingredientsList.size() == 0){
            Intent intent = new Intent(this, ShoppingListActivity.class);
            this.startActivity(intent);
        }
        String name = getIntent().getExtras().getString("name");
        TextView NameIndicator = findViewById(R.id.ShoppingListRecipeName);
        NameIndicator.setText("Missing ingredients for " + name);
        ShoppingListIngredientsAdapter.recipeName = name;
        RecyclerView siRV = findViewById(R.id.shoppingIngredientRV);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        siRV.setLayoutManager(llm);
        shoppingListIngredientsAdapter = new ShoppingListIngredientsAdapter(this,ingredientsList);
        ShoppingListIngredientsAdapter.sLIA = shoppingListIngredientsAdapter;
        siRV.setAdapter(shoppingListIngredientsAdapter);
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
