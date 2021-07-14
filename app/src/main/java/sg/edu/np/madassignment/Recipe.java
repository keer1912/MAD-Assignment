package sg.edu.np.madassignment;

import java.util.List;

public class Recipe {

    String name;
    String description;
    int time;
    boolean timeFormatIsHour;
    List<String> ingredients;
    String reqEquipment;
    int servingSize;
    String steps;

    String owner;
    int likes;

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

    public String getReqEquipment() {
        return reqEquipment;
    }

    public void setReqEquipment(String reqEquipment) {
        this.reqEquipment = reqEquipment;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
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
}
