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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCatalogueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<Exercise> list;

    Button bAddItem;
    DatabaseHelper myDb;
    ImageView exerciseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_catalogue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        exerciseImage = (ImageView) findViewById(R.id.ivExercise);
        bAddItem = (Button) findViewById(R.id.bAdd);


        list = myDb.exerciseSpin();
        Spinner spinner = (Spinner) findViewById(R.id.sExercise);

        List<String> exNames = new ArrayList<String> ();
        for (int i = 0; i < list.size(); i++)
        {
            exNames.add(list.get(i).getExName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        addItem();

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String name = parent.getItemAtPosition(pos).toString();

        TextView item = (TextView) findViewById(R.id.tvExerciseNames);
        TextView calories = (TextView) findViewById(R.id.tvCalories);


        // Used to display info
        for (Exercise f: list)
        {
            //checks to find mathing api level
            if (f.getExName().equalsIgnoreCase(name))
            {

                item.setText(f.getExName());
                calories.setText(Double.toString(f.getCalories()));

                if(f.getExName().toString().equals("Bicycling (5.5 mph)")){
                    exerciseImage.setImageResource(R.drawable.cycle);
                }

                else if(f.getExName().toString().equals("Dance, Aerobic, medium")) {
                    exerciseImage.setImageResource(R.drawable.dance);
                }

                else if(f.getExName().toString().equals("Running")) {
                    exerciseImage.setImageResource(R.drawable.running);
                }
                else if(f.getExName().toString().equals("Swimming, breast stroke, fast")) {
                    exerciseImage.setImageResource(R.drawable.swimming);
                }
                else {
                    exerciseImage.setImageResource(0);
                }
            }
        }
    }

    public void addItem(){
        bAddItem.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(ExerciseCatalogueActivity.this, ExerciseCatalogueForumActivity.class);
                        ExerciseCatalogueActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
