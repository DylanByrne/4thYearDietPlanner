package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DietEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView date, daily, userCal;
    private ArrayList<Diet> list;
    DatabaseHelper myDb;
    Button bDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        daily = (TextView) findViewById(R.id.tvDailyCal);
        date = (TextView) findViewById(R.id.tvDate);
        userCal = (TextView) findViewById(R.id.tvUserCal);

        Intent intent = getIntent();
        String selected = intent.getStringExtra("Date");

        date.setText(selected);

        list = myDb.dietSpin(myDb.retrieveUser(), selected);
        Spinner spinner = (Spinner) findViewById(R.id.dietSpin);

        List<String> dietNames = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            dietNames.add(list.get(i).getfoodName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dietNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        daily.setText(Double.toString(Calories(list)));
        userCal.setText(myDb.retrieveCalories(myDb.retrieveUser()));

        bDelete = (Button) findViewById(R.id.bAddDiet);

        removeDiet();



    }

    public void removeDiet() {
        bDelete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = (TextView) findViewById(R.id.tvDietName);
                        String currentSelected = item.getText().toString();
                        if(currentSelected.equalsIgnoreCase("Foodname")){
                            Toast.makeText(DietEditActivity.this, "Item Not Found", Toast.LENGTH_LONG).show();
                        }
                        else {
                            for (int i = 0; i < list.size(); i++) {
                                if (currentSelected.equals(list.get(i).foodName)) {
                                    Diet currentFood = list.get(i);
                                    myDb.deleteDiet(currentFood.getDietID());
                                    Toast.makeText(DietEditActivity.this, "Deleted Item " + currentFood.getfoodName(), Toast.LENGTH_LONG).show();
                                    Intent registerIntent = new Intent(DietEditActivity.this, DietPlanner.class);
                                    DietEditActivity.this.startActivity(registerIntent);
                                    break;
                                }
                            }
                        }
                    }
                }
        );
    }

    public double Calories(ArrayList<Diet> items){
        double total = 0;
        for(int i=0; i<items.size(); i++){
            total += items.get(i).getCalories();

        }
        return total;

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = (TextView) findViewById(R.id.tvDietName);
        TextView calories = (TextView) findViewById(R.id.tvCalories);
        TextView ingredients = (TextView) findViewById(R.id.tvIngredients);

        // Used to display info
        for (Diet d : list) {

            if (d.getfoodName().equalsIgnoreCase(name)) {

                item.setText(d.getfoodName());
                calories.setText(d.getCalories().toString());
                ingredients.setText(d.getIngredients().toString());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

