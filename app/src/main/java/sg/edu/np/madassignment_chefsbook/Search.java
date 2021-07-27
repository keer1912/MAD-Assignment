package sg.edu.np.madassignment_chefsbook;

import android.net.Uri;

import javax.annotation.Nullable;

public class Search {
    @Nullable
    private Uri Img;
    private String Name;
    private String Description;
    private String RecipeId;

    //Getter & Setter

    public Uri getImg() {
        return Img;
    }

    public void setImg(Uri img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(String recipeId) {
        RecipeId = recipeId;
    }
}
