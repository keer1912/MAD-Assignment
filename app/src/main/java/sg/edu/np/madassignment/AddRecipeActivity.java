package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    ImageView addImg;
    EditText rcpName;
    EditText rcpCategory;
    EditText rcpDescription;
    EditText rcpTime;
    EditText rcpIngredients;
    EditText rcpEquipment;
    EditText rcpSteps;
    EditText rcpServings;
    Button btnSubmit;

    private static final int PICK_IMG_REQUEST = 1;
    Uri mImageUri;
    TextView tvErrMsg;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        final FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-assignment-recipe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = db.getReference().child("recipes");

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        addImg = (ImageView)findViewById(R.id.addImage);
        addImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Get EditText
                rcpName = (EditText) findViewById(R.id.etName);
                rcpCategory = (EditText) findViewById(R.id.etCategory);
                rcpDescription = (EditText) findViewById(R.id.etDescription);
                rcpTime = (EditText) findViewById(R.id.etTime);
                rcpIngredients = (EditText) findViewById(R.id.etIngredients);
                rcpEquipment = (EditText) findViewById(R.id.etReqEquipment);
                rcpSteps = (EditText) findViewById(R.id.etSteps);
                rcpServings = (EditText) findViewById(R.id.etServingSize);
                TextView ErrMsg = (TextView)findViewById(R.id.tvErrMsg);

                String sName = rcpName.getText().toString();
                String sCategory = rcpCategory.getText().toString();
                String sDescription = rcpDescription.getText().toString();
                String sTime = rcpTime.getText().toString();
                String sIngredients = rcpIngredients.getText().toString();
                String sEquipment = rcpEquipment.getText().toString();
                String sSteps = rcpSteps.getText().toString();
                String sServing = rcpServings.getText().toString();

                // Check for incorrect input
                InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();

                if (inputValidatorHelper.isNullOrEmpty(sName)){
                    ErrMsg.setText("Name required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sCategory)){
                    ErrMsg.setText("Category required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sDescription)){
                    ErrMsg.setText("Description required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sTime)) {
                    ErrMsg.setText("Time required");
                    return;
                } else if (inputValidatorHelper.isNumeric(sTime)) {
                    ErrMsg.setText("Time must be numeric");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sIngredients)){
                    ErrMsg.setText("Ingredients required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sEquipment)){
                    ErrMsg.setText("Equipment required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sSteps)){
                    ErrMsg.setText("Steps required");
                    return;
                } else if (inputValidatorHelper.isNullOrEmpty(sServing)) {
                    ErrMsg.setText("Servings required");
                    return;
                } else if (inputValidatorHelper.isNumeric(sServing)) {
                ErrMsg.setText("Servings must be numeric");
                return;
                }

                //Create Model
                Recipe recipe = new Recipe();
                recipe.setName(sName);
                recipe.setCategory(sCategory);
                recipe.setDescription(sDescription);
                recipe.setTime(Integer.parseInt(sTime));
                recipe.setIngredients(new ArrayList<String>(Arrays.asList(sIngredients.split("\n"))));
                recipe.setReqEquipment(new ArrayList<String>(Arrays.asList(sEquipment.split("\\s*,\\s*"))));
                recipe.setSteps(new ArrayList<String>(Arrays.asList(sSteps.split("\n"))));
                recipe.setServingSize(Integer.parseInt(sServing));
                uploadRecipe(ref, recipe);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Bottom Nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Add);

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

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(addImg);
        }
    }

    private String getFileExtension(Uri uri) { // get file extension type  i.e. jpg, png, of the uri provided
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadRecipe(DatabaseReference ref, Recipe recipe) {

        StorageReference imgRef = mStorageRef.child("images/" + System.currentTimeMillis() + "." + getFileExtension(mImageUri));

        imgRef.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("Upload", "Img uploaded");
                        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                recipe.setImg(uri.toString());
                                ref.child("recipe" + System.currentTimeMillis()).setValue(recipe);
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Upload", "Img failed to upload");
                    }
                });



    }
}