package sg.edu.np.madassignment_chefsbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView userName;
    ImageView profilePic;
    TextView email;
    private FirebaseAuth mAuth;
    private ArrayList<CategoryFavourite> categoryList;
String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            userName = findViewById(R.id.userName);
            userName.setText(user.getDisplayName());
            UID = user.getUid();

            profilePic = findViewById(R.id.profilePic);
            //Toast.makeText(this.getApplicationContext(),user.getPhotoUrl().toString(),Toast.LENGTH_LONG).show();
            Picasso.with(this).load(user.getPhotoUrl()).into(profilePic);

            email = findViewById(R.id.email);
            email.setText(user.getEmail());
        }

        //sign out
        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sign out
                findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                        builder.setMessage("Are you sure you want to log out?")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mAuth.signOut();
                                        startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                                        finish();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();
                    }
                });
            }
        });

        //load category into listview
        /*
        Category:
            1) Breakfast
            2) Lunch & Dinner
            3) Beverage
            4) Appetizers
            5) Sides
            6) Desserts
            7) 30 minutes and Under
         */
        categoryList = new ArrayList<>();
        //Breakfast
        Drawable d = getResources().getDrawable(R.drawable.breakfast25);
        categoryList.add(new CategoryFavourite("Breakfast",0,d));
        //Lunch & Dinner
        d = getResources().getDrawable(R.drawable.main25);
        categoryList.add(new CategoryFavourite("Lunch & Dinner",0,d));
        //Beverage
        d = getResources().getDrawable(R.drawable.drink25);
        categoryList.add(new CategoryFavourite("Beverage",0,d));
        //Appetizers
        d = getResources().getDrawable(R.drawable.appetizer25);
        categoryList.add(new CategoryFavourite("Appetizers",0,d));
        //Sides
        d = getResources().getDrawable(R.drawable.side25);
        categoryList.add(new CategoryFavourite("Sides",0,d));
        //Desserts
        d = getResources().getDrawable(R.drawable.desserts25);
        categoryList.add(new CategoryFavourite(" Desserts",0,d));
        //30 minutes and Under
        d = getResources().getDrawable(R.drawable.under30min25);
        categoryList.add(new CategoryFavourite("30 minutes and Under",0,d));

        // Create the adapter to convert the array to views
        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview_category);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), FavRecipe.class);
                intent.putExtra("name", categoryList.get(position).getCategory());
                startActivity(intent);



            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase  =  firebaseDatabase.getReference().child("users").child(UID).child("Favourite");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() != null){

                    ArrayList<Recipe> favListOfTheUser = (ArrayList<Recipe>) snapshot.getValue();

                    //count the number of fav for each cat

                    //add to categoryList

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onStart() {  // Bottom Navigation is here so that it loads properly when user presses back
        super.onStart();
        //Bottom Nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Profile);

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
                        return true;
                }
                return false;
            }
        });
    }
}

class CategoryAdapter extends ArrayAdapter<CategoryFavourite> {

    //ArrayList<CategoryFavourite> categoryList;


    public CategoryAdapter(Context context, ArrayList<CategoryFavourite> categoryList) {
        super(context,0,categoryList);
        //this.categoryList = categoryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CategoryFavourite category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categoryfav, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvCount = (TextView) convertView.findViewById(R.id.tvCount);
        LinearLayout listview_item = (LinearLayout)convertView.findViewById(R.id.listview_item);
        // Populate the data into the template view using the data object
        tvName.setText(category.getCategory());
        tvCount.setText(category.getNumberOfFavourite() + "");
        listview_item.setBackground(category.getCategoryDrawable());
        listview_item.setMinimumHeight(100);
        // Return the completed view to render on screen
        return convertView;
    }
}