package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class ExercisePlanner extends AppCompatActivity {

    String userID;
    Button bAddExercise;
    DatabaseHelper myDb;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_planner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);
        bAddExercise = (Button) findViewById(R.id.bAddExercise);
        Exercise();

        calendar = (CalendarView) findViewById(R.id.cExercise);
        calendar.setMinDate(System.currentTimeMillis() - 10000);
        Calendar();


    }

    public void Exercise() {
        bAddExercise.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent registerIntent = new Intent(ExercisePlanner.this, ExerciseForumActivity.class);
                        ExercisePlanner.this.startActivity(registerIntent);

                    }
                }
        );
    }

    public void Calendar(){
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth+"/"+(month+1)+"/" +year;
                if(myDb.compareExerciseDate(date, myDb.retrieveUser()) == true) {
                    Intent registerIntent = new Intent(ExercisePlanner.this, ExerciseEditActivity.class);
                    registerIntent.putExtra("Date", date);
                    ExercisePlanner.this.startActivity(registerIntent);
                }
                else{

                }
            }
        });
    }

}
