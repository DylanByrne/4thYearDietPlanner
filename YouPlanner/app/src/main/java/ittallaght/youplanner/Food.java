package ittallaght.youplanner;

/**
 * Created by dylan on 19/04/2017.
 */

public class Food {

    int foodID;
    public int getFoodID() { return foodID; };
    String foodName;
    public String getfoodName() { return foodName; };
    Double calories;
    public Double getCalories() { return calories; };
    String ingredients;
    public String getIngredients() { return ingredients; };


    public Food(int id, String name, double calo, String ing){
        this.foodID = id;
        this.foodName = name;
        this.calories = calo;
        this.ingredients = ing;
    }
}
