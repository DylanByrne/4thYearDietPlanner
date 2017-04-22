package ittallaght.youplanner;

/**
 * Created by dylan on 20/04/2017.
 */

public class Exercise {

    int exID;
    public int getExID() { return exID; };
    String exName;
    public String getExName() { return exName; };
    Double calories;
    public Double getCalories() { return calories; };


    public Exercise(int id, String name, double calo){
        this.exID = id;
        this.exName = name;
        this.calories = calo;

    }


}
