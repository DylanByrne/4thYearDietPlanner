package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExerciseCatalogueForumActivity extends AppCompatActivity {

    EditText etExerciseName, etBurned;
    Button bAdd;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_catalogue_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);
        bAdd = (Button) findViewById(R.id.bAdd);
        etExerciseName = (EditText) findViewById(R.id.etExerciseName);
        etBurned = (EditText) findViewById(R.id.etBurned);

        AddData();



    }

    public void AddData() {
        bAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etExerciseName.getText().toString().equals("") || etBurned.getText().toString().equals("")){
                            Toast.makeText(ExerciseCatalogueForumActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
                        } else {
                            boolean pass = myDB.uniqueExercise(etExerciseName.getText().toString());

                            if (pass == true) {

                                boolean isInserted = myDB.insertExerciseCatlogue(
                                        etExerciseName.getText().toString(),
                                        etBurned.getText().toString()
                                );

                                if (isInserted == true) {
                                    Intent registerIntent = new Intent(ExerciseCatalogueForumActivity.this, ExerciseCatalogueActivity.class);
                                    ExerciseCatalogueForumActivity.this.startActivity(registerIntent);
                                    Toast.makeText(ExerciseCatalogueForumActivity.this, "Item Inserted", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(ExerciseCatalogueForumActivity.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(ExerciseCatalogueForumActivity.this, "Name Matches Item in Catalogue", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

}
