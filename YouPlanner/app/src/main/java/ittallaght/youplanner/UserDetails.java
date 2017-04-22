package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    TextView tvUserName, tvUserEmail, tvUserAge, tvUserGender, tvUserHeight, tvUserWeight, tvUserCal;
    Button bChangeWeight;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        bChangeWeight = (Button) findViewById(R.id.bChangeWeight);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        tvUserAge = (TextView) findViewById(R.id.etUserAge);
        tvUserGender = (TextView) findViewById(R.id.tvUserGender);
        tvUserHeight = (TextView) findViewById(R.id.tvHeight);
        tvUserWeight = (TextView) findViewById(R.id.tvUserWeight);
        tvUserCal = (TextView) findViewById(R.id.tvUserCalorie);

        int id = myDb.retrieveUser();

        User current = myDb.searchUser(id);

        tvUserName.setText(current.getNsme());
        tvUserEmail.setText(current.getEmail());
        tvUserAge.setText(Integer.toString(current.getAge()));
        tvUserGender.setText(current.getGender());
        tvUserHeight.setText(Double.toString(current.getHeight()));
        tvUserWeight.setText(Double.toString(current.getWeight()));
        tvUserCal.setText(Double.toString(current.getCalories()));
        ChangeWeight();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.menu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(UserDetails.this, MainMenuActivity.class);
                UserDetails.this.startActivity(registerIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




    public void ChangeWeight() {
        bChangeWeight.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(UserDetails.this, WeightEditActivity.class);
                        UserDetails.this.startActivity(registerIntent);
                    }
                }
        );
    }


}
