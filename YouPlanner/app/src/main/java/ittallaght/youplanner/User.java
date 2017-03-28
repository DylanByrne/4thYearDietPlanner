package ittallaght.youplanner;

/**
 * Created by dylan on 08/02/2017.
 */

public class User {

    @com.google.gson.annotations.SerializedName("name")
    private String name;
    @com.google.gson.annotations.SerializedName("email")
    private String email;
    @com.google.gson.annotations.SerializedName("age")
    private int age;
    @com.google.gson.annotations.SerializedName("password")
    private String password;
    @com.google.gson.annotations.SerializedName("id")
    private int mId;

    //Getter and Setter Methods

    public void setName(String nameIn){
        this.name = nameIn;
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String emailIn){
        this.email = emailIn;
    }

    public String getEmail(){
        return this.email;
    }

    public void setAge(int ageIn){
        this.age = ageIn;
    }

    public int getAge(){
        return this.age;
    }

    public void setPassword(String passwordIn){
        this.password = passwordIn;
    }

    public String getPassword(){
        return this.password;
    }

    //Default Constructor
    public User(){}

    public void setId(int idIn){
        this.mId = idIn;
    }

    public int getId(){
        return this.mId;
    }

    public User(String name, String email, int age, String password, int id){
        this.setName(name);
        this.setEmail(email);
        this.setAge(age);
        this.setPassword(password);
        this.setId(id);
    }

}
