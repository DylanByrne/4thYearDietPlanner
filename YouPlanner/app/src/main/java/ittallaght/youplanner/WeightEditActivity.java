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
import android.widget.TextView;
import android.widget.Toast;

public class WeightEditActivity extends AppCompatActivity {

    Button bChangeWeight;
    TextView tvOldWeight;
    EditText etNewWeight, etEmailConfirm;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        bChangeWeight = (Button) findViewById(R.id.bChangeWeight);
        tvOldWeight = (TextView) findViewById(R.id.tvCurrentWeight);
        etEmailConfirm = (EditText) findViewById(R.id.etEmailConfirm);
        etNewWeight = (EditText) findViewById(R.id.etNewWeight);

        tvOldWeight.setText(Double.toString(myDb.getWeight(myDb.retrieveUser())));

        UpdateWeight();


    }

    public void UpdateWeight() {
        bChangeWeight.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(etEmailConfirm.getText().toString().equals("") || etNewWeight.getText().toString().equals("") ) {
                            Toast.makeText(WeightEditActivity.this, "All Fields Must be Entered", Toast.LENGTH_LONG).show();
                        }
                        else{


                            boolean isUpdated = myDb.updateWeight(myDb.retrieveUser(),
                                    Double.parseDouble(etNewWeight.getText().toString()),
                                    etEmailConfirm.getText().toString()

                            );

                            if (isUpdated == true) {
                                Intent registerIntent = new Intent(WeightEditActivity.this, UserDetails.class);
                                WeightEditActivity.this.startActivity(registerIntent);
                                Toast.makeText(WeightEditActivity.this, "Weight Updated to: " + etNewWeight.getText() + "kg", Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(WeightEditActivity.this, "User does not Exist", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                }
        );
    }

}
