package sg.edu.np.madassignment_chefsbook;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class CategoryFavourite implements Serializable {

    private String category;
    private int numberOfFavourite;
    private Drawable categoryDrawable;

    public CategoryFavourite(String category, int numberOfFavourite, Drawable categoryDrawable) {
        this.category = category;
        this.numberOfFavourite = numberOfFavourite;
        this.categoryDrawable = categoryDrawable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfFavourite() {
        return numberOfFavourite;
    }

    public void setNumberOfFavourite(int numberOfFavourite) {
        this.numberOfFavourite = numberOfFavourite;
    }

    public Drawable getCategoryDrawable() {
        return categoryDrawable;
    }

    public void setCategoryDrawable(Drawable categoryDrawable) {
        this.categoryDrawable = categoryDrawable;
    }
}
