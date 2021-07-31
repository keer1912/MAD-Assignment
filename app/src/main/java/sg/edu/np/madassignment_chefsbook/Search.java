package sg.edu.np.madassignment_chefsbook;

import javax.annotation.Nullable;

public class Search {
    @Nullable
    private String Img;
    private String Name;
    private String Description;
    private String RecipeId;

    //Getter & Setter

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
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
