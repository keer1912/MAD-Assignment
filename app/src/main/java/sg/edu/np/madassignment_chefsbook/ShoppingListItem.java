package sg.edu.np.madassignment_chefsbook;

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

    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public int getIngredientsListCount(){
        return ingredientsList.size();
    }
}
