package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeUser;
    TextView welcomeText;
    TextView userName;
    private FirebaseAuth mAuth;
    private ArrayList<Recipe> list;

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rv);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            welcomeUser = findViewById(R.id.welcomeUser);
            welcomeText = findViewById(R.id.welcomeText);

            welcomeUser.setText("Welcome " + user.getDisplayName());
            welcomeText.setText("What do you want to cook today?");
        }

        recyclerView = findViewById(R.id.RecyclerView1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference mDatabase =  firebaseDatabase.getReference().child("recipes");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list = new ArrayList<>();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Recipe recipe = new Recipe();
                    recipe.setName(snapshot.child("name").getValue().toString());
                    recipe.setTime(Integer.valueOf(String.valueOf(snapshot.child("time").getValue())));
                    list.add(recipe);
                }
                homeAdapter = new HomeAdapter(mContext,list);
                Log.v("TEST", list.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}