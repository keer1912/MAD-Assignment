package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // THIS TIMER CODE WILL ONLY BE EXECUTED WHEN USER IS LOGGED IN.
        // if user is logged in the flow is SplashActivity ----> LoginActivity (happen in background) ----> HomeActivity
        //else if user is not then the flow is MainActivity ---->LoginActivity (shown) ---->HomeActivity

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}