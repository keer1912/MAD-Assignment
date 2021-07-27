package sg.edu.np.madassignment;

import java.util.ArrayList;

public class ShoppingListItem {
    String name;
    ArrayList<String> ingredientsList;

    public ShoppingListItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredientsList;
    }

    public void setIngredients(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
