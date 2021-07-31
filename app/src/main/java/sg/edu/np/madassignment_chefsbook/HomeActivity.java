package sg.edu.np.madassignment_chefsbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeUser;
    TextView welcomeText;
    TextView userName;
    private FirebaseAuth mAuth;
    Uri ImageUri;
    private ArrayList<Recipe> thirtyMinUnder;
    private ArrayList<Recipe> featuredRecipes;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    TextView category;
    TextView category2;
    RecipeItemAdapter recipeItemAdapter;
    RecipeItemAdapter recipeItemAdapter2;
    Context mContext;

    ProgressDialog TempDialog;
    CountDownTimer mCountDownTimer;
    int i=0;

    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure(DatabaseError error);
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.this.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rv);
        mContext = getApplicationContext();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            welcomeUser = findViewById(R.id.welcomeUser);
            welcomeText = findViewById(R.id.welcomeText);

            welcomeUser.setText("Welcome " + user.getDisplayName());
            welcomeText.setText("What do you want to cook today?");
        }

        recyclerView = findViewById(R.id.RecyclerView1);
        recyclerView2 = findViewById(R.id.RecyclerView2);
//      recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase =  firebaseDatabase.getReference().child("recipes");

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        thirtyMinUnder = new ArrayList<>();
        featuredRecipes = new ArrayList<>();

        ReadData(mDatabase, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                //whatever you need to do with the data
                for(DataSnapshot eachSnapshot: dataSnapshot.getChildren()){
                    if (eachSnapshot.child("name").getValue() == null) break;
                    //finding out the num of likes in each recipe inorder to check for the second rv
                    int likesNum = Integer.valueOf(eachSnapshot.child("likes").getValue().toString());

                    if (eachSnapshot.child("category").getValue().equals("30 minutes and Under")){
                        //each list will only have 10 elements
                        if(thirtyMinUnder.size()<10){
                            category = findViewById(R.id.category);
                            category.setText("30 minutes and under");

                            Recipe r = eachSnapshot.getValue(Recipe.class);
                            r.setRecipeId(eachSnapshot.getKey());

                            if (eachSnapshot.child("img").getValue() != null){
                                String img = eachSnapshot.child("img").getValue().toString();
                                r.setImg(img);
                                Log.d("DB", img);
                            }

                            //recipe.setIngredients(eachSnapshot.child("ingredients").getValue());
                            //Log.d("DB", eachSnapshot.child("time").getValue().toString());
                            thirtyMinUnder.add(r);
                        }
                    }
                    else if(likesNum >= 5){
                        //each list will only have 10 elements
                        if (featuredRecipes.size()<10){
                            // if recipes have more than certain num of likes , they will make to the featured recipe list instead
                            Recipe r = eachSnapshot.getValue(Recipe.class);
                            r.setRecipeId(eachSnapshot.getKey());
                            category2 = findViewById(R.id.category2);
                            category2.setText("Featured Recipe");


                            //recipe.setIngredients(eachSnapshot.child("ingredients").getValue());
                            //Log.e("string", r.ingredients.get(1));
                            //Log.d("DB", eachSnapshot.child("time").getValue().toString());
                            featuredRecipes.add(r);
                        }
                    }
                }
                recipeItemAdapter = new RecipeItemAdapter(mContext,thirtyMinUnder);
                recipeItemAdapter2 = new RecipeItemAdapter(mContext,featuredRecipes);
                recyclerView.setAdapter(recipeItemAdapter);
                recyclerView2.setAdapter(recipeItemAdapter2);
                Log.v("TEST", thirtyMinUnder.toString());
            }
            @Override
            public void onStart() {
                //whatever you need to do onStart
                Log.d("ONSTART", "Started");

            }

            @Override
            public void onFailure(DatabaseError error) {

            }
        });

        TempDialog = new ProgressDialog(HomeActivity.this);
        TempDialog.setCancelable(false);
        TempDialog.setProgress(i);


        TempDialog.show();
        mCountDownTimer = new CountDownTimer(3000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                TempDialog.setMessage("Retrieving Recipes...");
            }

            public void onFinish()
            {
                TempDialog.dismiss();

            }
        }.start();
    }


    public void ReadData(DatabaseReference ref ,final OnGetDataListener listener){
        listener.onStart();
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listener.onSuccess(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listener.onFailure(error);
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Bottom Nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Home);

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