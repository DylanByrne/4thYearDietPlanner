package ittallaght.youplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText etName, etAge, etEmail, etPassword, etPasswordConfirm, etHeight, etGender, etWeight;
    Button bRegister;
    String emailPattern, passwordPattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = (EditText) findViewById(R.id.etUserName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etGender = (EditText) findViewById(R.id.etGender);
        etWeight = (EditText) findViewById(R.id.etWeight);

        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        passwordPattern = "((?=.*\\d).{6,20})";

        bRegister = (Button) findViewById(R.id.btRegister);



        AddData();
    }

    public void AddData(){
        bRegister.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        if(etName.getText().toString().equals("") || etAge.getText().toString().equals("") || etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("") || etPasswordConfirm.getText().toString().equals("") || etHeight.getText().toString().equals("") || etGender.getText().toString().equals("") || etWeight.getText().toString().equals("")){
                            Toast.makeText(RegisterActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
                        }

                        else {
                            if (etEmail.getText().toString().matches(emailPattern))
                            {
                                boolean pass = myDb.isUnique(etEmail.getText().toString());

                                if (pass == true) {

                                    if(etPassword.getText().toString().matches(passwordPattern)) {
                                        //Checks passwords match
                                        if (etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {

                                            //Calculate and stores calorie intake
                                            double intake = calcDailyCal(Integer.parseInt(etAge.getText().toString()),
                                                    Integer.parseInt(etHeight.getText().toString()),
                                                    Integer.parseInt(etWeight.getText().toString()),
                                                    etGender.getText().toString()
                                            );

                                            if (intake == 0) {
                                                //do nothing
                                            } else {

                                                String calories = Double.toString(intake);

                                                boolean isInserted = myDb.insertUser(
                                                        etName.getText().toString(),
                                                        etEmail.getText().toString(),
                                                        etPassword.getText().toString(),
                                                        etAge.getText().toString(),
                                                        etHeight.getText().toString(),
                                                        etWeight.getText().toString(),
                                                        etGender.getText().toString(),
                                                        calories
                                                );

                                                if (isInserted == true) {
                                                    Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    RegisterActivity.this.startActivity(registerIntent);
                                                    Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                                }
                                                else {
                                                    Toast.makeText(RegisterActivity.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, "Passwords do not Match", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Password must contain one number and be longer than 6 characters but not more than 20", Toast.LENGTH_LONG).show();
                                    }


                                }

                                else {
                                    Toast.makeText(RegisterActivity.this, "Email must be unique", Toast.LENGTH_LONG).show();
                                }
                            }

                            else{
                                Toast.makeText(RegisterActivity.this, "Invalid Email Address", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

    public double calcDailyCal(int age,  double height, double weight, String gender){
        //using the calorie formula Mifflin-St. Jeor Equation to calculate expected intake

        double intake = 0;

        if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("Female")){
            intake = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        else if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("Male")){
            intake = 10 * weight + 6.25 * height - 5 * age + 5;
        }

        else{
            intake = 0;
            Toast.makeText(RegisterActivity.this, "Invalid Gender", Toast.LENGTH_LONG).show();
        }

        return intake;

    }

}
