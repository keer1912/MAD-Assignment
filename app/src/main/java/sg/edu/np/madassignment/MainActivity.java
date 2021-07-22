package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView[] dots;
    LinearLayout layout;
    ViewPager2 viewPager_slider;
    ArrayList<OnBoardPageItems> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SHARED PREFERENCES
        SharedPreferences preferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes")){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else{
            //else..
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }


        //test
        viewPager_slider = findViewById(R.id.viewpager);
        layout = findViewById(R.id.dots_container);

        dots = new TextView[3];

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

        setIndicators();
        viewPager_slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedDots(position);
                super.onPageSelected(position);
            }
        });
    }
    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setTextColor(getResources().getColor(R.color.purple));
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.light_grey));
            }
        }
    }

    private void setIndicators() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(24);
            layout.addView(dots[i]);
        }

    }

}