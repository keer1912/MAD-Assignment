package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FavRecipe extends AppCompatActivity {
    TextView favHeader;
    RecyclerView favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_recipe);

        favHeader = findViewById(R.id.favHeader);
//        favHeader.setText()   //categoryname

        favList = findViewById(R.id.favList);

        Intent intent = getIntent();
        String category = intent.getStringExtra("name");
        favHeader.setText(category);


    }
}