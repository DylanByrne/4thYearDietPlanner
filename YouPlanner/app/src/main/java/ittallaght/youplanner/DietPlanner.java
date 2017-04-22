package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class DietPlanner extends AppCompatActivity {

    String userID;
    Button bAddDiet;
    DatabaseHelper myDb;
    CalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);
        bAddDiet = (Button) findViewById(R.id.bAddDiet);
        Diet();

        calendar = (CalendarView) findViewById(R.id.cDiet);
        calendar.setMinDate(System.currentTimeMillis() - 10000);
        Calendar();


    }

    public void Diet() {
        bAddDiet.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent registerIntent = new Intent(DietPlanner.this, DietForumActivity.class);
                        DietPlanner.this.startActivity(registerIntent);

                    }
                }
        );
    }

    public void Calendar(){
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth+"/"+(month+1)+"/" +year;

                if(myDb.compareDietDate(date, myDb.retrieveUser()) == true) {

                    Intent registerIntent = new Intent(DietPlanner.this, DietEditActivity.class);
                    registerIntent.putExtra("Date", date);
                    DietPlanner.this.startActivity(registerIntent);

                }

                else{

                }
            }
        });
    }

}
