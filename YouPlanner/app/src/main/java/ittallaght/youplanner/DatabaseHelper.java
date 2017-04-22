package ittallaght.youplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by dylan on 16/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Assign db name
    public static final String DATABASE_NAME = "YouPlanner.db";
    //Assign table name
    public static final String USER_TABLE = "user_table";
    public static final String DIET_TABLE = "diet_table";
    public static final String EXERCISE_TABLE = "exercise_table";
    public static final String FOOD_CATALOGUE = "catalogue_table";
    public static final String EXERCISE_CATALOGUE = "exercise_catlogue_Table";


    public static final String User_Col_1 = "USERID";
    public static final String User_Col_2 = "USERNAME";
    public static final String User_Col_3 = "EMAIL";
    public static final String User_Col_4 = "PASSWORD";
    public static final String User_Col_5 = "AGE";
    public static final String User_Col_6 = "HEIGHT";
    public static final String User_Col_7 = "WEIGHT";
    public static final String User_Col_8 = "GENDER";
    public static final String User_Col_9 = "DAILYCAL";

    public static final String Food_Col_1 = "FOODNAME";
    public static final String Food_Col_2 = "CALORIES";
    public static final String Food_Col_3 = "CATEGORY";
    public static final String Food_Col_4 = "INGREDIANTS";

    public static final String Diet_Col_2 = "FOODNAME";
    public static final String Diet_Col_3 = "DIETDATE";
    public static final String Diet_Col_4 = "CALORIES";
    public static final String Diet_Col_5 = "INGREDIANTS";
    public static final String Diet_Col_6 = "UID";
    public static final String Diet_Col_7 = "FID";

    public static final String Exercise_Cat_2 = "EXERCISENAME";
    public static final String Exercise_Cat_3 = "CALORIESBURNED";

    public static final String Exercise_Col_2 = "EXERCISENAME";
    public static final String Exercise_Col_3 = "EXERCISEDATE";
    public static final String Exercise_Col_4 = "DURATION";
    public static final String Exercise_Col_5 = "CALORIESBURNED";
    public static final String Exercise_Col_6 = "UID";
    public static final String Exercise_Col_7 = "EID";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + "(USERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, AGE TEXT, HEIGHT TEXT, WEIGHT TEXT, GENDER TEXT, DAILYCAL TEXT)");
        db.execSQL("create table " + FOOD_CATALOGUE + "(FOODID INTEGER PRIMARY KEY AUTOINCREMENT, FOODNAME TEXT, CALORIES TEXT, CATEGORY TEXT, INGREDIANTS TEXT)");
        db.execSQL("create table " + EXERCISE_CATALOGUE + "(EXERCISEID INTEGER PRIMARY KEY AUTOINCREMENT, EXERCISENAME TEXT, CALORIESBURNED TEXT)");
        db.execSQL("create table " + DIET_TABLE + "(DIETID INTEGER PRIMARY KEY AUTOINCREMENT, FOODNAME TEXT, DIETDATE TEXT, CALORIES TEXT, INGREDIANTS TEXT, UID INTEGER, FID INTEGER, FOREIGN KEY (UID) REFERENCES USER_TABLE(USERID), FOREIGN KEY (FID) REFERENCES "+ FOOD_CATALOGUE + "(FOODID))");
        db.execSQL("create table " + EXERCISE_TABLE + "(EXERCISEID INTEGER PRIMARY KEY AUTOINCREMENT, EXERCISENAME TEXT, EXERCISEDATE TEXT, DURATION TEXT, CALORIESBURNED TEXT, UID INTEGER, EID INTEGER, FOREIGN KEY (UID) REFERENCES USER_TABLE(USERID), FOREIGN KEY (EID) REFERENCES "+ EXERCISE_CATALOGUE +"(EXERCISEID))");

        db.execSQL("create table CURRENT_USER (CURRENTUSERID INTEGER PRIMARY KEY)");

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST '" + EXERCISE_TABLE +"'");
        db.execSQL("DROP TABLE IF EXIST '" + DIET_TABLE+ "'");
        db.execSQL("DROP TABLE IF EXIST '" + EXERCISE_CATALOGUE+ "'");
        db.execSQL("DROP TABLE IF EXIST '" + FOOD_CATALOGUE+ "'");
        db.execSQL("DROP TABLE IF EXIST '" + USER_TABLE +"'");
        db.execSQL("DROP TABLE IF EXIST 'CURRENT_USER'");
        onCreate(db);
        db.close();

    }


    public void FoodItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + FOOD_CATALOGUE;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){

        }

        else {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(Food_Col_1, "Spicy meatballs with chilli black beans");
            contentValues1.put(Food_Col_2, "376");
            contentValues1.put(Food_Col_3, "Meat");
            contentValues1.put(Food_Col_4, "Red Onions, Garlic Cloves, Ground Cumin, Cherry Tomatoes, Avocado, Turkey Breast Mince");

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(Food_Col_1, "Tandoori chicken");
            contentValues2.put(Food_Col_2, "171");
            contentValues2.put(Food_Col_3, "Meat");
            contentValues2.put(Food_Col_4, "Lemons, Paprika, Red Onions, Chick Thighs, Vegetable Oil, Vegetable Oil, Ground Cumin");

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(Food_Col_1, "Miso-roasted aubergine steaks with sweet potato");
            contentValues3.put(Food_Col_2, "344");
            contentValues3.put(Food_Col_3, "Veg");
            contentValues3.put(Food_Col_4, "Large Aubergine, Brown Miso Paste, Sweet Potatoes, Sunflower Oil, Ginger, Garlic Clove, Sale, Spring Onions, Pasley leaves");

            ContentValues contentValues4 = new ContentValues();
            contentValues4.put(Food_Col_1, "Coconut & squash dhansak");
            contentValues4.put(Food_Col_2, "320");
            contentValues4.put(Food_Col_3, "Veg");
            contentValues4.put(Food_Col_4, "Vegetable Oil, Buttenut Squash, Onions, Mild Curry Paste, Tomatoes, Coconut Milk, Naan Bread, Lentils, Spinach, Coconut Yogurt");


            db.insert(FOOD_CATALOGUE, null, contentValues1);
            db.insert(FOOD_CATALOGUE, null, contentValues2);
            db.insert(FOOD_CATALOGUE, null, contentValues3);
            db.insert(FOOD_CATALOGUE, null, contentValues4);
        }
        db.close();

    }

    public boolean insertFood(String name, String calories, String type, String ingredients)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food_Col_1, name);
        contentValues.put(Food_Col_2, calories);
        contentValues.put(Food_Col_3, type);
        contentValues.put(Food_Col_4, ingredients);


        long result = db.insert(FOOD_CATALOGUE, null, contentValues);
        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }


    }

    public Boolean uniqueFood(String name){
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT FOODNAME FROM " + FOOD_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        String foodName;
        boolean pass = true;

        if(cursor.moveToFirst()){
            do{
                foodName = cursor.getString(0);


                if(foodName.equals(name)){

                    pass = false;
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }


    public void ExerciseItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + EXERCISE_CATALOGUE;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
            if(icount>0){

        }

        else {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(Exercise_Cat_2, "Bicycling (5.5 mph)");
            contentValues1.put(Exercise_Cat_3, "0.029");

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(Exercise_Cat_2, "Dance, Aerobic, medium");
            contentValues2.put(Exercise_Cat_3, "0.046");

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(Exercise_Cat_2, "Running");
            contentValues3.put(Exercise_Cat_3, "0.115");

            ContentValues contentValues4 = new ContentValues();
            contentValues4.put(Exercise_Cat_2, "Swimming, breast stroke, fast");
            contentValues4.put(Exercise_Cat_3, "0.074");

            db.insert(EXERCISE_CATALOGUE, null, contentValues1);
            db.insert(EXERCISE_CATALOGUE, null, contentValues2);
            db.insert(EXERCISE_CATALOGUE, null, contentValues3);
            db.insert(EXERCISE_CATALOGUE, null, contentValues4);
        }
    }

    public boolean insertExerciseCatlogue(String name, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise_Cat_2, name);
        contentValues.put(Exercise_Cat_3, calories);

        long result = db.insert(EXERCISE_CATALOGUE, null, contentValues);
        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean uniqueExercise(String name){
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT EXERCISENAME FROM " + EXERCISE_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        String exName;
        boolean pass = true;

        if(cursor.moveToFirst()){
            do{
                exName = cursor.getString(0);


                if(exName.equals(name)){

                    pass = false;
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }




    public boolean insertUser(String name, String email, String password, String age, String height, String weight, String gender, String dailyCal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Col_2, name);
        contentValues.put(User_Col_3, email);
        contentValues.put(User_Col_4, password);
        contentValues.put(User_Col_5, age);
        contentValues.put(User_Col_6, height);
        contentValues.put(User_Col_7, weight);
        contentValues.put(User_Col_8, gender);
        contentValues.put(User_Col_9, dailyCal);


        long result = db.insert(USER_TABLE, null, contentValues);
        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public boolean insertDiet(String name, String date, String calories, String ingrediants, int userID, int foodID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Diet_Col_2, name);
        contentValues.put(Diet_Col_3, date);
        contentValues.put(Diet_Col_4, calories);
        contentValues.put(Diet_Col_5, ingrediants);
        contentValues.put(Diet_Col_6, userID);
        contentValues.put(Diet_Col_7, foodID);

        long result = db.insert(DIET_TABLE, null, contentValues);
        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public void deleteDiet(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DIET_TABLE, "DIETID = ?", new String[] {Integer.toString(id)});
    }


    public boolean insertExercise(String name, String date, String duration, String calories, int userID, int foodID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise_Col_2, name);
        contentValues.put(Exercise_Col_3, date);
        contentValues.put(Exercise_Col_4, duration);
        contentValues.put(Exercise_Col_5, calories);
        contentValues.put(Exercise_Col_6, userID);
        contentValues.put(Exercise_Col_7, foodID);

        long result = db.insert(EXERCISE_TABLE, null, contentValues);
        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public void deleteExercise(int id, String duration)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXERCISE_TABLE, "EXERCISEID = ? AND DURATION = ? " , new String[] {Integer.toString(id), duration});
    }




    public String searchPass(String address)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT EMAIL, PASSWORD FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String email;
        String pass = "NOI FOUND";

        if(cursor.moveToFirst()){
            do{

                email = cursor.getString(0);

                if(email.equals(address)){

                    pass = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }

        db.close();
        return pass;
    }

    public User searchUser(int id)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        int identifier;
        String name = "";
        String email = "";
        String password  = "";
        int age  = 0;
        double height  = 0;
        double weight = 0;
        String gender = "";
        double cal = 0;

        if(cursor.moveToFirst()){
            do{

                identifier = Integer.parseInt(cursor.getString(0));

                if(identifier == id){

                    name = cursor.getString(1);
                    email = cursor.getString(2);
                    password = cursor.getString(3);
                    age = Integer.parseInt(cursor.getString(4));
                    height =Double.parseDouble(cursor.getString(5));
                    weight = Double.parseDouble(cursor.getString(6));
                    gender = cursor.getString(7);
                    cal = Double.parseDouble(cursor.getString(8));


                    break;



                }
            }
            while(cursor.moveToNext());
        }

        User currentUser = new User(id,name,email,password,age,height,weight,gender,cal);
        db.close();
        return currentUser;
    }

    public Boolean isUnique(String email){
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT EMAIL FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String address;
        boolean pass = true;

        if(cursor.moveToFirst()){
            do{
                address = cursor.getString(0);


                if(address.equals(email)){

                    pass = false;
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }

    public Integer searchID(String uname)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT USERID, EMAIL FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String userName;
        int user = 3000;

        if(cursor.moveToFirst()){
            do{
                userName = cursor.getString(1);

                if(userName.equals(uname)){

                    user = Integer.parseInt(cursor.getString(0));
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public String searchUserName(String uname)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT USERNAME, EMAIL FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String userName;
        String user = "";

        if(cursor.moveToFirst()){
            do{
                userName = cursor.getString(1);

                if(userName.equals(uname)){

                    user = cursor.getString(0);
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public void assignUser(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CURRENT_USER", null, null);
        String currentuser = "CURRENTUSERID";

        ContentValues contentValues = new ContentValues();
        contentValues.put(currentuser, id);
        db.insert("CURRENT_USER", null, contentValues);
        db.close();
    };

    public double getWeight(int id)
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String query = "SELECT USERID, WEIGHT FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int userID;
        double user = 0;

        if(cursor.moveToFirst()){
            do{
                userID = Integer.parseInt(cursor.getString(0));

                if(userID == id){

                    user = Double.parseDouble(cursor.getString(1));
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public int retrieveUser()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT CURRENTUSERID FROM CURRENT_USER";
        Cursor cursor = db.rawQuery(query, null);
        int user = -1;
        if(cursor.moveToFirst()) {

            user = Integer.parseInt(cursor.getString(0));

        }
        db.close();
        return user;
    };

    public void signOut()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CURRENT_USER", null, null);
        db.close();
    }


    public ArrayList<Food> foodSpin(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FOOD_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Food> list = new ArrayList<Food>();
        if(cursor.moveToFirst()){
            do{
                Food f = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)), cursor.getString(4));
                list.add(f);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;

    }

    public ArrayList<Diet> dietSpin(int user, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DIET_TABLE + " WHERE UID = " +user+ " AND DIETDATE = '" +date+ "'";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Diet> list = new ArrayList<Diet>();
        if(cursor.moveToFirst()){
            do{
                Diet d = new Diet(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                list.add(d);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;

    }

    public ArrayList<Exercise> exerciseSpin(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCISE_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Exercise> list = new ArrayList<Exercise>();
        if(cursor.moveToFirst()){
            do{
                Exercise e = new Exercise(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)));
                list.add(e);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;

    }

    public ArrayList<Regime> regiemeSpin(int user, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCISE_TABLE + " WHERE UID = " +user+ " AND EXERCISEDATE = '" +date+ "'";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Regime> list = new ArrayList<Regime>();
        if(cursor.moveToFirst()){
            do{
                Regime r = new Regime(Integer.parseInt(cursor.getString(0)), (Integer.parseInt(cursor.getString(0))+" "+cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Double.parseDouble(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                list.add(r);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;

    }


    public boolean updateUser(String userEmail, String userName, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query = "SELECT USERNAME, EMAIL FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String email, user;
        long result = -1;

        if(cursor.moveToFirst()){
            do{

                user = cursor.getString(0);
                email = cursor.getString(1);

                if(userName.equals(user)){

                    if(email.equals(userEmail)){
                        String[] whereArgs = {email};
                        contentValues.put(User_Col_4, password);
                        result = db.update(USER_TABLE, contentValues, User_Col_3+" =? " , whereArgs );
                        break;
                    }
                }
            }
            while(cursor.moveToNext());
        }

        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public boolean updateWeight(int id, double weight, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        String query = "SELECT USERID, EMAIL, AGE, HEIGHT, GENDER FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String emailCheck;
        int idCheck, age;
        String gender;
        double height;
        long result = -1;
        double newCal = 0;

        if(cursor.moveToFirst()){
            do{

                idCheck = Integer.parseInt(cursor.getString(0));
                emailCheck = cursor.getString(1);
                height = Double.parseDouble(cursor.getString(3));
                gender = cursor.getString(4);
                age = Integer.parseInt(cursor.getString(2));

                if(id == idCheck ){
                    {
                        if (email.equals(emailCheck)) {

                            if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("Female")){
                                newCal = 10 * weight + 6.25 * height - 5 * age - 161;
                            }

                            else if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("Male")){
                                newCal = 10 * weight + 6.25 * height - 5 * age + 5;
                            }

                            else{
                                newCal = 0;
                            }

                            String[] whereArgs = {email};
                            contentValues1.put(User_Col_9, newCal);
                            contentValues1.put(User_Col_7, weight);
                            result = db.update(USER_TABLE, contentValues1, User_Col_3+" =? " , whereArgs );

                            break;


                        }
                    }
                }
            }
            while(cursor.moveToNext());
        }

        db.close();

        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public String retrieveCalories(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT USERID, DAILYCAL FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String cal= "" ;
        int user;
        if(cursor.moveToFirst()) {
            do{
                user = Integer.parseInt(cursor.getString(0));

                if(user == id){

                    cal = cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return cal;
    };

    public double retrieveDailyCalories(int user, String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT UID, DIETDATE, CALORIES FROM " + DIET_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int userCheck;
        String dateCheck;
        double dailycal = 0;
        double calories = 0;
        if(cursor.moveToFirst()) {
            do{
                userCheck = Integer.parseInt(cursor.getString(0));
                dateCheck = cursor.getString(1);
                if(user == userCheck){
                    if(date.equals(dateCheck)){

                        dailycal = Double.parseDouble(cursor.getString(2));
                        calories += dailycal;

                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return calories;
    };

    public boolean compareDietDate(String date, int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DIETDATE, UID FROM " + DIET_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String dateCheck;
        int idCheck;
        boolean check = false;


        if(cursor.moveToFirst()) {
            do{
                idCheck = Integer.parseInt(cursor.getString(1));
                dateCheck = cursor.getString(0);
                    if(date.equals(dateCheck)){
                        if(id == idCheck){
                            check = true;
                            break;
                        }
                    }

            }
            while(cursor.moveToNext());
        }
        db.close();
        return check;
    };

    public boolean compareExerciseDate(String date, int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT EXERCISEDATE, UID FROM " + EXERCISE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String dateCheck;
        int idCheck;
        boolean check = false;


        if(cursor.moveToFirst()) {
            do{
                idCheck = Integer.parseInt(cursor.getString(1));
                dateCheck = cursor.getString(0);
                if(date.equals(dateCheck)) {
                    if(id == idCheck) {
                        check = true;
                        break;
                    }
                }



            }
            while(cursor.moveToNext());
        }
        db.close();
        return check;
    };

    public void closeDb()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.close();
    }


}