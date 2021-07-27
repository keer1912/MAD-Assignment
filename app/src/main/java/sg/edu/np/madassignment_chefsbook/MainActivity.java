package sg.edu.np.madassignment_chefsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            Toast.makeText(MainActivity.this, "Welcome to Chef's Book!", Toast.LENGTH_LONG)
                    .show();
        }
        else {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }, 3000);
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
        .putBoolean("isFirstRun", false).commit();
    }
}