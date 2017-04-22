package ittallaght.youplanner;

/**
 * Created by dylan on 19/04/2017.
 */

public class Diet {

    int dietID;
    public int getDietID() { return dietID; };
    String foodName;
    public String getfoodName() { return foodName; };
    String date;
    public String getDate() { return date; };
    Double calories;
    public Double getCalories() { return calories; };
    String ingredients;
    public String getIngredients() { return ingredients; };

    int uId;
    public int getuId() { return uId; };
    int fId;
    public int getfId() { return fId;};


    public Diet(int id, String name, String date,double calo, String ing, int uid, int fid){
        this.dietID = id;
        this.foodName = name;
        this.date = date;
        this.calories = calo;
        this.ingredients = ing;
        this.uId = uid;
        this.fId = fid;
    }


}
