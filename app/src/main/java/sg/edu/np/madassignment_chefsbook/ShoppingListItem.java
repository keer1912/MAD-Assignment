package sg.edu.np.madassignment_chefsbook;

import android.widget.ImageView;

import java.util.ArrayList;

public class ShoppingListItem {
    String name;
    ArrayList<String> ingredientsList;
    String img;

    public ShoppingListItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIngredientsListCount(){
        return ingredientsList.size();
    }
}
