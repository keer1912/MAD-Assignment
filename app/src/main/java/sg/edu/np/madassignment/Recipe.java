package sg.edu.np.madassignment;

import android.net.Uri;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Recipe {

    String name;
    String description;
    int time;
    boolean timeFormatIsHour;
    List<String> ingredients;
    List<String> reqEquipment;
    int servingSize;
    List<String> steps;
    String category;
    String owner;
    int likes;
    String img;
    String difficulty;
    public Recipe() {
    }

    //Getter & Setter

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

    public boolean isTimeFormatIsHour() {
        return timeFormatIsHour;
    }

    public void setTimeFormatIsHour(boolean timeFormatIsHour) {
        this.timeFormatIsHour = timeFormatIsHour;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getReqEquipment() {
        return reqEquipment;
    }

    public void setReqEquipment(List<String> reqEquipment) {
        this.reqEquipment = reqEquipment;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
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
