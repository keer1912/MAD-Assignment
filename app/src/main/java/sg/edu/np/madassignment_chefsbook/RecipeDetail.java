package sg.edu.np.madassignment_chefsbook;

import android.graphics.drawable.Drawable;
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
    TextView showRecipeServing;
    TextView showRecipeOwner;
    TextView showRecipeDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        //Shopping list
        RecipeDetailIngredientAdapter.recipeName = getIntent().getExtras().get("name").toString();


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
        showRecipeLikes.setText(ReceiveRecipeLikes + " likes");

        String ReceiveRecipeTime = getIntent().getExtras().get("time").toString();
        showRecipeTime = findViewById(R.id.ShowTime);
        showRecipeTime.setText("Time\n" + ReceiveRecipeTime + " minutes");

        String ReceiveRecipeDifficulty = getIntent().getExtras().get("difficulty").toString();
        showRecipeDifficulty = findViewById(R.id.ShowDifficulty);
        showRecipeDifficulty.setText("Difficulty\n" + ReceiveRecipeDifficulty);

        String ReceiveServingSize = getIntent().getExtras().get("servingSize").toString();
        showRecipeServing = findViewById(R.id.ShowServingSize);
        showRecipeServing.setText("Serving Size\n" + ReceiveServingSize+ " persons");

        String ReceiveDesc = getIntent().getExtras().get("description").toString();
        showRecipeDesc = findViewById(R.id.description);
        showRecipeDesc.setText(ReceiveDesc);

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

    }
}