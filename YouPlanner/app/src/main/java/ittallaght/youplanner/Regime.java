package ittallaght.youplanner;

/**
 * Created by dylan on 20/04/2017.
 */

public class Regime {

    int exID;
    public int getExID() { return exID; };
    String exName;
    public String getExName() { return exName; };
    String date;
    public String getDate() { return date; };
    int duration;
    public int getDuration() { return duration; };
    double calories;
    public double getCalories() { return calories; };
    int uId;
    public int getuId() { return uId; };
    int fId;
    public int getfId() { return fId;};


    public Regime(int id, String name, String date, int dur, double calo, int uid, int fid){
        this.exID = id;
        this.exName = name;
        this.date = date;
        this.duration = dur;
        this.calories = calo;
        this.uId = uid;
        this.fId = fid;
    }
}
