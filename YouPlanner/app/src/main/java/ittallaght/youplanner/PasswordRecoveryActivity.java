package ittallaght.youplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordRecoveryActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etPasswordConfirm, etName;
    Button bUpdatePassword;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etName = (EditText) findViewById(R.id.etName);


        bUpdatePassword = (Button) findViewById(R.id.bUpdatePass);

        UpdatePassword();


    }

    public void UpdatePassword() {
        bUpdatePassword.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {


                            boolean isUpdated = myDb.updateUser(
                                    etEmail.getText().toString(),
                                    etName.getText().toString(),
                                    etPassword.getText().toString()
                            );

                            if (isUpdated == true) {
                                Intent registerIntent = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
                                PasswordRecoveryActivity.this.startActivity(registerIntent);
                                Toast.makeText(PasswordRecoveryActivity.this, "Password Updated", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(PasswordRecoveryActivity.this, "User does not Exist", Toast.LENGTH_LONG).show();
                            }
                        }

                        else{
                            Toast.makeText(PasswordRecoveryActivity.this, "Passwords Failed to Match", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
