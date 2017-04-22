package ittallaght.youplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DietForumActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Calendar calendar = Calendar.getInstance();

    TextView date;
    private ArrayList<Food> list;
    DatabaseHelper myDb;
    Button bDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");

        date = (TextView) findViewById(R.id.tvDate);

        date.setText(df.format(c.getTime()));
        String currentDate = date.getText().toString();
        selectDate();

        list = myDb.foodSpin();
        Spinner spinner = (Spinner) findViewById(R.id.dietSpin);

        List<String> foodNames = new ArrayList<String> ();
        for (int i = 0; i < list.size(); i++)
        {
            foodNames.add(list.get(i).getfoodName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        bDiet = (Button) findViewById(R.id.bAddDiet);

        addDiet();

    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = (TextView) findViewById(R.id.tvDietName);
        TextView calories = (TextView) findViewById(R.id.tvCalories);
        TextView ingredients = (TextView) findViewById(R.id.tvIngredients);

        // Used to display info
        for (Food f: list)
        {
            //checks to find mathing api level
            if (f.getfoodName().equalsIgnoreCase(name))
            {

                item.setText(f.getfoodName());
                calories.setText(f.getCalories().toString());
                ingredients.setText(f.getIngredients());
            }
        }
    }


    public void addDiet() {
        bDiet.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = (TextView) findViewById(R.id.tvDietName);
                        String currentSelected = item.getText().toString();
                        if(currentSelected.equalsIgnoreCase("Foodname")){
                            Toast.makeText(DietForumActivity.this, "Item Not Found", Toast.LENGTH_LONG).show();

                        }
                        else{
                            for (int i = 0; i < list.size(); i++)
                            {

                                if(currentSelected.equals(list.get(i).foodName)){
                                    Food currentFood = list.get(i);
                                    boolean isInserted = myDb.insertDiet(currentFood.getfoodName(), date.getText().toString(), currentFood.getCalories().toString(), currentFood.getIngredients(), myDb.retrieveUser(), currentFood.getFoodID());


                                    if(isInserted == true){
                                        Intent registerIntent = new Intent(DietForumActivity.this, DietPlanner.class);
                                        DietForumActivity.this.startActivity(registerIntent);
                                        Toast.makeText(DietForumActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(DietForumActivity.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    //Toast.makeText(DietForumActivity.this, "Cannot Find Diet", Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                    }
                }
        );
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

    public void selectDate() {
        date.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(DietForumActivity.this, listener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                }
        );
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date.setText(dayOfMonth+"/"+(month+1)+"/" +year );
        }
    };





}
