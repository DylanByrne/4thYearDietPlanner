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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExerciseForumActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Calendar calendar = Calendar.getInstance();

    TextView date;
    private ArrayList<Exercise> list;
    DatabaseHelper myDb;
    Button bExercise;
    EditText duration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");

        date = (TextView) findViewById(R.id.tvDate);
        duration = (EditText) findViewById(R.id.etDuration);

        date.setText(df.format(c.getTime()));
        String currentDate = date.getText().toString();
        selectDate();

        list = myDb.exerciseSpin();
        Spinner spinner = (Spinner) findViewById(R.id.exerciseSpin);

        List<String> exNames = new ArrayList<String> ();
        for (int i = 0; i < list.size(); i++)
        {
            exNames.add(list.get(i).getExName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        bExercise = (Button) findViewById(R.id.bAddExercise);

        addExercise();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = (TextView) findViewById(R.id.tvExerciseName);
        TextView calories = (TextView) findViewById(R.id.tvCalories);


        // Used to display info
        for (Exercise f: list)
        {
            //checks to find mathing api level
            if (f.getExName().equalsIgnoreCase(name))
            {

                item.setText(f.getExName());
                calories.setText(Double.toString(f.getCalories()));
            }
        }
    }


    public void addExercise() {
        bExercise.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = (TextView) findViewById(R.id.tvExerciseName);
                        String currentSelected = item.getText().toString();
                        if(currentSelected.equalsIgnoreCase("EXERCISENAME")) {
                            Toast.makeText(ExerciseForumActivity.this, "Item Not Found", Toast.LENGTH_LONG).show();

                        }
                        else {

                            if (duration.getText().toString().equals("")) {
                                Toast.makeText(ExerciseForumActivity.this, "Must Set Duration for Exercise", Toast.LENGTH_LONG).show();
                            }


                            else{
                                  for (int i = 0; i < list.size(); i++) {


                                      if (currentSelected.equals(list.get(i).exName)) {

                                          Exercise currentExercise = list.get(i);
                                          boolean isInserted = myDb.insertExercise(currentExercise.getExName(), date.getText().toString(), duration.getText().toString(), currentExercise.getCalories().toString(), myDb.retrieveUser(), currentExercise.getExID());


                                          if (isInserted == true) {
                                              Intent registerIntent = new Intent(ExerciseForumActivity.this, ExercisePlanner.class);
                                              ExerciseForumActivity.this.startActivity(registerIntent);
                                              Toast.makeText(ExerciseForumActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                          } else {
                                              Toast.makeText(ExerciseForumActivity.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                          }
                                      } else {


                                      }

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
                        new DatePickerDialog(ExerciseForumActivity.this, listener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
