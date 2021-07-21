package sg.edu.np.madassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

        ArrayList<String> ar1 =getIntent().getExtras().getStringArrayList("ingredients");
        listView = findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ar1);
        listView.setAdapter(adapter);
        Log.e("string",ar1.get(0));



    }
}