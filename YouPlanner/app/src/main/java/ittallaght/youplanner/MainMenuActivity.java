package ittallaght.youplanner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenuActivity extends AppCompatActivity {

    Button bDiet, bExercise, bCatalogue, bSignOut, bExerciseCat;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bDiet = (Button) findViewById(R.id.bDiet);
        bExercise = (Button) findViewById(R.id.bExercise);
        bCatalogue = (Button) findViewById(R.id.bCatalogue);
        bSignOut = (Button) findViewById(R.id.bSignOut);
        bExerciseCat = (Button) findViewById(R.id.bExeciseCat);


        Diet();
        Exercise();
        Catalogue();
        SignOut();
        ExerciseCat();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.weight);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainMenuActivity.this, UserDetails.class);
                MainMenuActivity.this.startActivity(registerIntent);
            }
        });
    }



    public void Diet() {
        bDiet.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainMenuActivity.this, DietPlanner.class);
                        registerIntent.putExtra("myId", userID);
                        MainMenuActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }


     public void Exercise() {
        bExercise.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainMenuActivity.this, ExercisePlanner.class);
                        MainMenuActivity.this.startActivity(registerIntent);
                    }
                }
        );
     }

     public void Catalogue() {
        bCatalogue.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainMenuActivity.this, CatalogueActivity.class);
                        MainMenuActivity.this.startActivity(registerIntent);
                    }
                }
        );
     }

    public void ExerciseCat() {
        bExerciseCat.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainMenuActivity.this, ExerciseCatalogueActivity.class);
                        MainMenuActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }


    public void SignOut() {
        bSignOut.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent registerIntent = new Intent(MainMenuActivity.this, LoginActivity.class);
                        MainMenuActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }
}
