package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class ExerciseEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView date, burnCal, dailyCal, difference, duration;
    private ArrayList<Regime> list;
    DatabaseHelper myDb;
    Button bDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        burnCal = (TextView) findViewById(R.id.tvCalories);
        date = (TextView) findViewById(R.id.tvDate);
        dailyCal = (TextView) findViewById(R.id.tvDailyCal);


        Intent intent = getIntent();
        String selected = intent.getStringExtra("Date");

        date.setText(selected);

        list = myDb.regiemeSpin(myDb.retrieveUser(), selected);
        Spinner spinner = (Spinner) findViewById(R.id.sRegieme);

        List<String> exerciseNames = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            exerciseNames.add(list.get(i).getExName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exerciseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        int user = myDb.retrieveUser();
        double dailyCalories = myDb.retrieveDailyCalories(user,date.getText().toString());

        dailyCal.setText(Double.toString(dailyCalories));

        bDelete = (Button) findViewById(R.id.bDeleteRegieme);

        removeDiet();



    }

    public void removeDiet() {
        bDelete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = (TextView) findViewById(R.id.tvExerciseName);
                        String currentSelected = item.getText().toString();
                        if(currentSelected.equalsIgnoreCase("Exercisename")){
                            Toast.makeText(ExerciseEditActivity.this, "Item Not Found", Toast.LENGTH_LONG).show();
                        }
                        else {
                            for (int i = 0; i < list.size(); i++) {
                                if (currentSelected.equals(list.get(i).getExName())) {
                                    Regime currentExercise = list.get(i);
                                    myDb.deleteExercise(list.get(i).getExID(), Integer.toString(list.get(i).getDuration()));
                                    Toast.makeText(ExerciseEditActivity.this, "Deleted Item " + currentExercise.getExName(), Toast.LENGTH_LONG).show();
                                    Intent registerIntent = new Intent(ExerciseEditActivity.this, ExercisePlanner.class);
                                    ExerciseEditActivity.this.startActivity(registerIntent);
                                    break;
                                }
                            }
                        }
                    }
                }
        );
    }





    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = (TextView) findViewById(R.id.tvExerciseName);
        TextView calories = (TextView) findViewById(R.id.tvCalories);
        TextView duration = (TextView) findViewById(R.id.tvDuration);
        TextView difference = (TextView) findViewById(R.id.tvDifference);

        // Used to display info
        for (Regime r : list) {

            if (r.getExName().equalsIgnoreCase(name)) {
                Double caloriesBurned = caloriesBurned(myDb.getWeight(myDb.retrieveUser()),r.getCalories(),r.getDuration());
                Double diff = calorieDifference(Double.parseDouble(dailyCal.getText().toString()),caloriesBurned);
                item.setText(r.getExName());
                calories.setText(String.format( "%.2f", caloriesBurned));
                duration.setText(Integer.toString(r.getDuration()));
                difference.setText(String.format( "%.2f", diff));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public double calorieDifference(double dailyCalories, double caloriesBurned){

        double difference;
        difference = dailyCalories - caloriesBurned;

        return difference;

    }

    public double caloriesBurned(double weight, double caloriesBurned, int duration){

        double burnedCalories;
        // convert kg to pounds
        burnedCalories = ((weight*2.2)* caloriesBurned) * duration;



        return burnedCalories;

    }
}
