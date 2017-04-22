package ittallaght.youplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etPassword, etEmail;
    Button bLogin;
    TextView registerLink, passwordReset;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        myDb.FoodItems();
        myDb.ExerciseItems();
        myDb.signOut();

        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        passwordReset = (TextView) findViewById(R.id.tvPasswordReset);

        Register();
        Login();
        PasswordReset();


    }

    public void Register() {
        registerLink.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                        LoginActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    public void PasswordReset() {
        passwordReset.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
                        LoginActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    public void Login(){
        bLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String password = etPassword.getText().toString();
                        String identifier = etEmail.getText().toString();

                        String pass = myDb.searchPass(identifier);


                        if(password.equals(pass)){

                            Integer userID = myDb.searchID(identifier);


                            myDb.assignUser(userID);
                            Toast.makeText(LoginActivity.this, "Welcome user: " + myDb.searchUserName(identifier), Toast.LENGTH_LONG).show();
                            Intent registerIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                            LoginActivity.this.startActivity(registerIntent);



                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Username and Password do not match" , Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


}
