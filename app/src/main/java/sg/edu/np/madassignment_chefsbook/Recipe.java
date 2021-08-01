package sg.edu.np.madassignment_chefsbook;

import java.util.ArrayList;

public class Recipe{

    String name;
    String description;
    int time;
    ArrayList<String> ingredients;
    ArrayList<String> reqEquipment;
    int servingSize;
    ArrayList<String> steps;
    String category;
    String owner;
    int likes;
    String img;
    String difficulty;
    String RecipeId;

    public Recipe() {
    }

    public Recipe(String recipeId) {
        this.RecipeId = recipeId;
    }

    public Recipe(String name, String category) {
        this.name = name;
        this.category = category;
    }

    //Getter & Setter
    public String getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(String RecipeId) {
        this.RecipeId = RecipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getReqEquipment() {
        return reqEquipment;
    }

    public void setReqEquipment(ArrayList<String> reqEquipment) {
        this.reqEquipment = reqEquipment;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
