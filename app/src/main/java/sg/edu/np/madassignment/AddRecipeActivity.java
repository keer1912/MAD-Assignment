package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    EditText rcpName;
    EditText rcpDescription;
    EditText rcpTime;
    Button rcpTimeFormatIsHour;
    List<EditText> rcpIngredients;
    EditText rcpSteps;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }
}