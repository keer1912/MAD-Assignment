package sg.edu.np.madassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity {

    TextView showRecipeName;
    TextView showRecipeCategory;
    TextView showRecipeLikes;
    TextView showRecipeTime;
    TextView showRecipeDifficulty;
    TextView showRecipeOwner;
    TextView showIngredients;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        String ReceiveRecipeOwner = getIntent().getExtras().get("owner").toString();
        showRecipeOwner = findViewById(R.id.recipeOwner);
        showRecipeOwner.setText(ReceiveRecipeOwner);

        String ReceiveRecipeName = getIntent().getExtras().get("name").toString();
        showRecipeName = findViewById(R.id.Rname);
        showRecipeName.setText(ReceiveRecipeName);

        String ReceiveRecipeCategory = getIntent().getExtras().get("category").toString();
        showRecipeCategory = findViewById(R.id.catName);
        showRecipeCategory.setText(ReceiveRecipeCategory);

        String ReceiveRecipeLikes = getIntent().getExtras().get("likes").toString();
        showRecipeLikes = findViewById(R.id.numLikes);
        showRecipeLikes.setText(ReceiveRecipeLikes);

        String ReceiveRecipeTime = getIntent().getExtras().get("time").toString();
        showRecipeTime = findViewById(R.id.time);
        showRecipeTime.setText(ReceiveRecipeTime);

        String ReceiveRecipeDifficulty = getIntent().getExtras().get("difficulty").toString();
        showRecipeDifficulty = findViewById(R.id.difficulty);
        showRecipeDifficulty.setText(ReceiveRecipeDifficulty);

        // INGREDIENT RV
        ArrayList<String> ingredientsList = getIntent().getExtras().getStringArrayList("ingredients");
        RecyclerView IngredientRecyclerView = findViewById(R.id.ingredientRV);
        RecipeDetailIngredientAdapter ingredientAdapter = new RecipeDetailIngredientAdapter(ingredientsList);
        LinearLayoutManager mlm1 = new LinearLayoutManager(this);
        IngredientRecyclerView.setLayoutManager(mlm1);
        IngredientRecyclerView.setAdapter(ingredientAdapter);

        //STEPS RV
        ArrayList<String> stepsList = getIntent().getExtras().getStringArrayList("steps");
        RecyclerView StepsRecyclerView = findViewById(R.id.stepsRV);
        RecipeDetailStepsAdapter stepAdapter = new RecipeDetailStepsAdapter(stepsList);
        LinearLayoutManager mlm2 = new LinearLayoutManager(this);
        StepsRecyclerView.setLayoutManager(mlm2);
        StepsRecyclerView.setAdapter(stepAdapter);

        //Log.e("string",ingredientsList.get(0));
    }
}