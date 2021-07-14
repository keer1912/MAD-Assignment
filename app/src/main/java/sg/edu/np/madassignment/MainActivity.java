package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager_slider;
    ArrayList<OnBoardPageItems> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test
        viewPager_slider = findViewById(R.id.viewpager);
        int[] images = {R.drawable.chef,R.drawable.community,R.drawable.share};
        String[] heading = {"Cooking experience like a chef ","Learn how the community cooks","Share your recipes with others"};
        String[] desc = {"Let's make a delicious dish with the best recipes for you and your family",
                "Exploring your community taste buds and what food means to them",
                "Sharing recipes and sharing the joy of food with others"};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i =0; i< images.length ; i++){
            OnBoardPageItems onBoardItem = new OnBoardPageItems(images[i],heading[i],desc[i]);
            viewPagerItemArrayList.add(onBoardItem);
        }

        OnBoardPageAdapter OBAdapter = new OnBoardPageAdapter(viewPagerItemArrayList);
        viewPager_slider.setAdapter(OBAdapter);

        Button get_started = findViewById(R.id.getStarted);
        get_started.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

    }
}