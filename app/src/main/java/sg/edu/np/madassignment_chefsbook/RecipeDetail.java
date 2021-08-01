package sg.edu.np.madassignment_chefsbook;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    ImageView recipeImg;
    TextView showRecipeName;
    TextView showRecipeCategory;
    TextView showRecipeLikes;
    TextView showRecipeTime;
    TextView showRecipeDifficulty;
    TextView showRecipeServing;
    TextView showRecipeOwner;
    TextView showRecipeDesc;
    ImageButton favButton;
    private FirebaseAuth mAuth;
    ArrayList<Recipe> favRecipeArrayList;
    String imageURL = "";
    int minute = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        //Shopping list
        //RecipeDetailIngredientAdapter.recipeName = getIntent().getExtras().get("name").toString();

        recipeImg = findViewById(R.id.recipeImg);
        showRecipeOwner = findViewById(R.id.recipeOwner);
        showRecipeName = findViewById(R.id.Rname);
        showRecipeCategory = findViewById(R.id.catName);
        showRecipeDesc = findViewById(R.id.description);
        showRecipeLikes = findViewById(R.id.numLikes);
        showRecipeTime = findViewById(R.id.ShowTime);
        showRecipeServing = findViewById(R.id.ShowServingSize);
        showRecipeDifficulty = findViewById(R.id.ShowDifficulty);
        favButton = findViewById(R.id.FavButton);
        favRecipeArrayList = new ArrayList<Recipe>();
        //
        Boolean favChecker = false;


        /*String ReceiveRecipeOwner = getIntent().getExtras().get("owner").toString();
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
        showRecipeTime = findViewById(R.id.ShowServingSize);
        showRecipeTime.setText("Time\n" + ReceiveRecipeTime + " minutes");

        String ReceiveRecipeDifficulty = getIntent().getExtras().get("difficulty").toString();
        showRecipeDifficulty = findViewById(R.id.ShowDifficulty);
        showRecipeDifficulty.setText("Difficulty\n" + ReceiveRecipeDifficulty);

        String ReceiveServingSize = getIntent().getExtras().get("servingSize").toString();
        showRecipeServing = findViewById(R.id.ShowTime);
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
        StepsRecyclerView.setAdapter(stepAdapter);*/

        String ReceiveID = getIntent().getExtras().get("id").toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase =  firebaseDatabase.getReference().child("recipes").child(ReceiveID);

        RecyclerView IngredientRecyclerView = findViewById(R.id.ingredientRV);
        LinearLayoutManager mlm1 = new LinearLayoutManager(this);
        IngredientRecyclerView.setLayoutManager(mlm1);

        RecyclerView StepsRecyclerView = findViewById(R.id.stepsRV);
        LinearLayoutManager mlm2 = new LinearLayoutManager(this);
        StepsRecyclerView.setLayoutManager(mlm2);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("name").getValue() != null) {
                        if (snapshot.exists()) {
                            Recipe r = snapshot.getValue(Recipe.class);
                            //Shopping List
                            RecipeDetailIngredientAdapter.recipeName = r.name;
                            RecipeDetailIngredientAdapter.recipeID = ReceiveID;

                            showRecipeName.setText(r.name);
                            showRecipeOwner.setText(r.owner);
                            showRecipeCategory.setText(r.category);
                            showRecipeDesc.setText(r.description);
                            showRecipeLikes.setText(r.likes + "");
                            showRecipeTime.setText("Time\n" + r.time + " minutes");
                            showRecipeServing.setText("Serving Size\n" + r.servingSize);
                            showRecipeDifficulty.setText("Difficulty\n" + r.difficulty);

                            minute =  r.time;

                            if (snapshot.child("img").getValue() != null) {
                                String img = snapshot.child("img").getValue().toString();
                                imageURL = snapshot.child("img").getValue().toString();
                                Picasso.with(getApplicationContext()).load(img).into(recipeImg);

                                //Shopping List
                                RecipeDetailIngredientAdapter.img = img;
                            }
                            RecipeDetailIngredientAdapter ingredientAdapter = new RecipeDetailIngredientAdapter(r.ingredients);
                            IngredientRecyclerView.setAdapter(ingredientAdapter);

                            RecipeDetailStepsAdapter stepsAdapter = new RecipeDetailStepsAdapter(r.steps);
                            StepsRecyclerView.setAdapter(stepsAdapter);
                        }
                    };
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        //get user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null) {
            String UID = user.getUid();
            mDatabase =  firebaseDatabase.getReference().child("users").child(UID).child("Favourite");

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.getValue() != null){
                        //Creating a HashMap object

                        favRecipeArrayList = (ArrayList<Recipe>) snapshot.getValue();
                    } else {
                        favRecipeArrayList = new ArrayList<Recipe>();
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(user!= null){
                    String UID = user.getUid();
                    //firebase insertion of
                    DatabaseReference mDatabase =  firebaseDatabase.getReference().child("users").child(UID).child("Favourite");

                    if(!favRecipeArrayList.contains(new Recipe(ReceiveID))){

                        Recipe r = new Recipe(ReceiveID, showRecipeCategory.getText().toString());
                        r.setRecipeId(ReceiveID);
                        r.setImg(imageURL);
                        r.setName(showRecipeName.getText().toString());
                        r.setTime(minute);
                        favRecipeArrayList.add(r);


                    }
                    mDatabase.setValue(favRecipeArrayList);

//                    favChecker = true;
//
//                    favref.addValueEventListener(new ValueEventListener(){
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot){
//                            if(favChecker.equals(true)){
//                                if (snapshot.child(postkey).hasChild(currentUserid)){
//                                    favref.child(postkey).child(currentUserid).removeValue();
//                                    delete(time);
//                                    favChecker = false;
//
//                                }
//                            }
//                        }
//                    })


                }


            }
        });

    }

}


