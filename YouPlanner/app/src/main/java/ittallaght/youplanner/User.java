package ittallaght.youplanner;

/**
 * Created by dylan on 21/04/2017.
 */

public class User {

    int id;
    String name;
    String email;
    String password;
    int age;
    double height;
    double weight;
    String gender;
    double calories;

    public int getId(){
        return id;
    }
    public String getNsme(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getGender(){
        return gender;
    }
    public double getHeight(){
        return height;
    }
    public double getWeight(){
        return weight;
    }
    public int getAge(){
        return age;
    }
    public double getCalories(){
        return calories;
    }
    public User(){};


    public User( int id,String name, String email, String password, int age,  double height, double weight, String gender, double calories){
        this.id =id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.calories = calories;
    }



}
